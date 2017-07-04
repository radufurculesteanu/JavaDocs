package Angajati;

import Animale.Animal;
import Animale.AnimalZooFeroce;

/**
 * Created by Radu.Furculesteanu on 7/4/2017.
 */
public class VeterinarZoo implements AngajatZoo {
    private int nrActiuni;

    public void lucreaza(Animal animal)
    {
        this.nrActiuni++;
        System.out.println("Veterinatul are grija de animal");
        if (animal instanceof AnimalZooFeroce)
            animal.faceBaie();
    }

    public int bonusSalarial()
    {
        return this.nrActiuni * AngajatZoo.valoareBonusPerAnimal * 2;
    }
}
