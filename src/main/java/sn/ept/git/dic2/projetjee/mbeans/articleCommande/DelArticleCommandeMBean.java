package sn.ept.git.dic2.projetjee.mbeans.articleCommande;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import org.primefaces.PrimeFaces;
import sn.ept.git.dic2.projetjee.entities.ArticleCommande;
import sn.ept.git.dic2.projetjee.entities.ArticleCommande;
import sn.ept.git.dic2.projetjee.facades.ArticleCommandeFacade;

import java.io.Serializable;
import java.util.List;

@Named("delArticleCommandeMBean")
@ViewScoped
public class DelArticleCommandeMBean implements Serializable {
    @EJB
    private ArticleCommandeFacade articleCommandeFacade;
    private ArticleCommande articleCommande;
    private String message;
    private ArticleCommande selectedArticleCommande;

    private List<ArticleCommande> selectedArticlesCommande;

    private List<ArticleCommande> articlesCommande;

    @PostConstruct
    public void init() {
        this.articlesCommande = articleCommandeFacade.findAll();
    }

    public ArticleCommande getArticleCommande() {
        return articleCommande;
    }

    public void setArticleCommande(ArticleCommande articleCommande) {
        this.articleCommande = articleCommande;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public ArticleCommandeFacade getArticleCommandeFacade() {
        return articleCommandeFacade;
    }

    public void setArticleCommandeFacade(ArticleCommandeFacade articleCommandeFacade) {
        this.articleCommandeFacade = articleCommandeFacade;
    }

    public ArticleCommande getSelectedArticleCommande() {
        return selectedArticleCommande;
    }

    public void setSelectedArticleCommande(ArticleCommande selectedArticleCommande) {
        this.selectedArticleCommande = selectedArticleCommande;
    }

    public List<ArticleCommande> getSelectedArticlesCommande() {
        return selectedArticlesCommande;
    }

    public void setSelectedArticlesCommande(List<ArticleCommande> selectedArticlesCommande) {
        this.selectedArticlesCommande = selectedArticlesCommande;
    }

    public List<ArticleCommande> getArticlesCommande() {
        return articlesCommande;
    }

    public void setArticlesCommande(List<ArticleCommande> articlesCommande) {
        this.articlesCommande = articlesCommande;
    }

    public void delete(ArticleCommande articleCommande) {
        System.out.println("Supprimer la articleCommande : " + articleCommande);
        articleCommandeFacade.remove(articleCommande);
        message = "Supprimé avec succès";


    }

    public void deleteArticleCommande() {
        articleCommandeFacade.remove(selectedArticleCommande);
        articlesCommande.remove(selectedArticleCommande);
        this.selectedArticleCommande = null;

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Article Removed"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-articles");
    }

    public String getDeleteButtonMessage() {
        if (hasSelectedArticlesCommande()) {
            int size = this.selectedArticlesCommande.size();
            return size > 1 ? size + " articles selected" : "1 article selected";
        }

        return "Delete";
    }

    public boolean hasSelectedArticlesCommande() {
        return this.selectedArticlesCommande != null && !this.selectedArticlesCommande.isEmpty();
    }

    public void deleteSelectedArticlesCommande() {
        for (ArticleCommande article : selectedArticlesCommande) {
            articleCommandeFacade.remove(article);
        }
        articlesCommande.removeAll(selectedArticlesCommande);
        this.selectedArticlesCommande = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Articles Removed"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-articles");
        PrimeFaces.current().executeScript("PF('dtArticles').clearFilters()");
    }

}
