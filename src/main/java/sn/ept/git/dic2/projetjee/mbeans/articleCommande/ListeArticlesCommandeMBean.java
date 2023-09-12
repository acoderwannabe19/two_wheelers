package sn.ept.git.dic2.projetjee.mbeans.articleCommande;

import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import sn.ept.git.dic2.projetjee.entities.ArticleCommande;
import sn.ept.git.dic2.projetjee.facades.ArticleCommandeFacade;

import java.io.Serializable;
import java.util.List;

@Named("listeArticlesCommandeMBean")
@ViewScoped
public class ListeArticlesCommandeMBean implements Serializable {

    @EJB
    private ArticleCommandeFacade articleCommandeFacade;

    private ArticleCommande articleCommandeChoisi;

    private List<ArticleCommande> articleCommandes;

    public ArticleCommandeFacade getArticleCommandeFacade() {
        return articleCommandeFacade;
    }

    public void setArticleCommandeFacade(ArticleCommandeFacade articleCommandeFacade) {
        this.articleCommandeFacade = articleCommandeFacade;
    }

    public ArticleCommande getArticleCommandeChoisi() {
        return articleCommandeChoisi;
    }

    public void setArticleCommandeChoisi(ArticleCommande articleCommandeChoisi) {
        this.articleCommandeChoisi = articleCommandeChoisi;
    }

    public List<ArticleCommande> getArticleCommandes() {
        System.out.println("Liste des articleCommandes " + articleCommandes);
        if(articleCommandes == null) {
            articleCommandes = articleCommandeFacade.findAll();
        }
        return articleCommandes;
    }

    public void setArticleCommandes(List<ArticleCommande> articleCommandes) {
        this.articleCommandes = ListeArticlesCommandeMBean.this.articleCommandes;
    }

}
