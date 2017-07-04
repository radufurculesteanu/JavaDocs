import Angajati.AngajatZoo;
import Angajati.IngrijitorZoo;
import Angajati.VeterinarZoo;
import Animale.*;

/**
 * Created by Radu.Furculesteanu on 7/4/2017.
 */
public class GradinaZooMain {
    public static void main(String[] args) {
        AnimalZooRar animal1 = new AnimalZooRar("Pinguin");
        AnimalZooRar animal2 = new AnimalZooRar("Elefant","Africa");
        AnimalZooRar animal3 = new AnimalZooRar();

        AnimalZooFeroce animalFeroce = new AnimalZooFeroce();
        AngajatZoo angajat1 = new IngrijitorZoo();
        IngrijitorZoo angajat2 = new IngrijitorZoo();
        AngajatZoo angajat3 = new VeterinarZoo();
        VeterinarZoo angajat4 = new VeterinarZoo();

        angajat3.lucreaza(animal1);
        angajat3.lucreaza(animal2);
        angajat3.lucreaza(animal3);
        System.out.println("--------------------");
        angajat4.lucreaza(animal1);
        angajat4.lucreaza(animal2);
        angajat4.lucreaza(animal3);
        System.out.println("--------------------");
        angajat1.lucreaza(animal1);
        angajat1.lucreaza(animal2);
        angajat1.lucreaza(animal3);
        System.out.println("--------------------");
        angajat2.lucreaza(animal1);
        angajat2.lucreaza(animal2);
        angajat2.lucreaza(animal3);
        System.out.println("--------------------");
        try
        {
            //angajat2.lucreaza(animal1,null);
            angajat2.lucreaza(animalFeroce,angajat1);
            angajat2.lucreaza(animal1,new String("Mancare"));
        }
        catch(AnimalManancaOmException amoe)
        {
            System.out.println("Animalul nu poate manca un angajat!");
        }
        catch(AnimalPeCaleDeDisparitieException apcd)
        {
            System.out.println("Animal pe cale de disparitie/animal flamand");
        }

        catch(AnimalManancaAnimalException amae)
        {
            System.out.println("Animalul nu poate manca alt animal!");
        }
        System.out.println("--------------------");

        try
        {
            angajat2.lucreaza(animalFeroce);
            angajat2.lucreaza(animalFeroce,null);
            //angajat2.lucreaza(animalFeroce, new String("Mancare"));

        }
        catch(AnimalPeCaleDeDisparitieException apcd)
        {
            System.out.println("Animal pe cale de disparitie/animal flamand");
        }
        catch(AnimalManancaOmException amoe)
        {
            System.out.println("Animalul nu poate manca un angajat!");
        }
        catch(AnimalManancaAnimalException amae)
        {
            System.out.println("Animalul nu poate manca alt animal!");
        }

        System.out.println("Final!");
        System.out.println("Bonus angajat 2: " + angajat2.bonusSalarial());
        System.out.println("Bonus angajat 4: " + angajat4.bonusSalarial());
    }


}
