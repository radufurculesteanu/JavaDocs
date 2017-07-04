package Animale;

import Angajati.AngajatZoo;

/**
 * Created by Radu.Furculesteanu on 7/4/2017.
 */
public class AnimalZooFeroce extends Animal {



    public void faceBaie()
    {
        System.out.println("Animalul feroce face baie");
    }

    public void seJoaca()
    {
        System.out.println("Animalul feroce se joaca");
    }

    public void doarme()
    {
        super.doarme();
        System.out.println("Animalul feroce vaneaza");
    }

    public void mananca(Object obj) throws AnimalManancaOmException, AnimalManancaAnimalException
    {
        if(obj instanceof AngajatZoo)
            throw new AnimalManancaOmException();
        if(obj instanceof Animal)
            throw new AnimalManancaAnimalException();
        else
            System.out.println("Animalul feroce mananca");
    }
}
