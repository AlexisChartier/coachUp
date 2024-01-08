package coachup.dao;

import coachup.model.Categorie;

import java.util.List;

public abstract class CategorieDAO {
    public abstract List<Categorie> getAllCategories();
    public abstract Categorie getCategorieById(int id);
    public abstract int addCategorie(Categorie category);
    public abstract void updateCategorie(Categorie category);
    public abstract void deleteCategorie(int id);
}
