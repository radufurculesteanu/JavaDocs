package Animale;

import Angajati.AngajatZoo;

/**
 * Created by Radu.Furculesteanu on 7/4/2017.
 */
public class AnimalZooRar extends Animal {
    private String nume;
    private String numeleTariiOrigine;

    public AnimalZooRar()
    {
        super();
        this.nume = "n/a";
        this.numeleTariiOrigine = "n/a";
    }

    public AnimalZooRar(String nume)
    {
        super();
        this.nume = nume;
        this.numeleTariiOrigine = "n/a";
    }

    public AnimalZooRar(String nume, String numeTara)
    {
        super();
        this.nume = nume;
        this.numeleTariiOrigine = numeTara;
    }

    public String getNume()
    {
        return this.nume;
    }

    public String getNumeleTariiOrigine()
    {
        return this.numeleTariiOrigine;
    }

    public void faceBaie()
    {
        System.out.println("AnimalulZooRar face baie");
    }

    public void seJoaca()
    {
        System.out.println("AnimalulZooRar se joaca");
        super.doarme();
    }

    public void mananca(Object obj) throws AnimalManancaOmException, AnimalManancaAnimalException
    {
        if(obj instanceof AngajatZoo)
            throw new AnimalManancaOmException();
        if(obj instanceof Animal)
            throw new AnimalManancaAnimalException();
        else
            System.out.println("AnimalZooRar mananca");
    }

}
