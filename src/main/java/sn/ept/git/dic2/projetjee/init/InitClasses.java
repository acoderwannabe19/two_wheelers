package sn.ept.git.dic2.projetjee.init;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.stream.Collectors;

@Singleton
@Startup
public class InitClasses {

    @PersistenceContext(name = "velo")
    private EntityManager entityManager;

    @PostConstruct
    private void init() {
        System.out.println("Entities initialization");

        try {
            // Read the SQL file
            InputStream inputStream = getClass().getResourceAsStream("/vente_velos_final.sql");
            String sqlScript = convertInputStreamToString(inputStream);

            // Execute SQL statements
            executeSqlScript(sqlScript);

            System.out.println("Database initialization completed successfully.");
        } catch (IOException e) {
            System.err.println("Error reading SQL file: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Error executing SQL statements: " + e.getMessage());
        }
    }

    private String convertInputStreamToString(InputStream inputStream) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            return reader.lines().collect(Collectors.joining("\n"));
        }
    }

    private void executeSqlScript(String sqlScript) throws SQLException {
        Connection connection = entityManager.unwrap(Connection.class);
        try (Statement statement = connection.createStatement()) {
            // Split SQL statements (assuming they are separated by a semicolon)
            String[] statements = sqlScript.split(";");

            // Execute each statement
            for (String sql : statements) {
                // Trim leading/trailing white spaces and ignore empty statements
                sql = sql.trim();
                if (!sql.isEmpty()) {
                    statement.execute(sql);
                }
            }
        }

    }
}
