package Angajati;

import Animale.*;

/**
 * Created by Radu.Furculesteanu on 7/4/2017.
 */
public class IngrijitorZoo implements AngajatZoo{
    private int nrActiuni;

    public void lucreaza(Animal animal)
    {
        this.nrActiuni++;
        System.out.println("Ingrijitorul intra in cusca animalului");
    }

    public IngrijitorZoo()
    {

    }

    public IngrijitorZoo(int nrActiuni)
    {
        this.nrActiuni = nrActiuni;
    }

    public int getNrActiuni()
    {
        return this.nrActiuni;
    }

    public void lucreaza(Animal animal, Object mancare) throws AnimalPeCaleDeDisparitieException, AnimalManancaOmException, AnimalManancaAnimalException
    {
        this.nrActiuni++;
        this.lucreaza(animal);
        animal.seJoaca();
        animal.faceBaie();
        if(animal instanceof AnimalZooRar || mancare == null)
            throw new AnimalPeCaleDeDisparitieException();
        else
            animal.mananca(mancare);
    }

    public int bonusSalarial()
    {
        return this.nrActiuni * valoareBonusPerAnimal * 3;
    }
}
