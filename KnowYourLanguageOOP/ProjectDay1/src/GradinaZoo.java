import Angajati.IngrijitorZoo;
import Animale.AnimalZooRar;

import java.util.Date;

/**
 * Created by Radu.Furculesteanu on 7/4/2017.
 */
public final class GradinaZoo {
    private final String denumireGradinaZoo;
    private final Date dataDeschideriiGradinii;
    private final AnimalZooRar animalRar;
    private final IngrijitorZoo angajatulLunii;

    public GradinaZoo(String denumire, Date data, AnimalZooRar animalRar, IngrijitorZoo angajatulLunii)
    {
        this.denumireGradinaZoo = denumire;
        this.dataDeschideriiGradinii = new Date(data.getTime());
        this.animalRar = new AnimalZooRar(animalRar.getNume(),animalRar.getNumeleTariiOrigine());
        this.angajatulLunii = new IngrijitorZoo(angajatulLunii.getNrActiuni());
    }

    public String getDenumireGradinaZoo()
    {
        return this.denumireGradinaZoo;
    }

    public Date getDataDeschideriiGradinii()
    {
        return new Date(this.dataDeschideriiGradinii.getTime());
    }

    public AnimalZooRar getAnimalRar()
    {
        return new AnimalZooRar(this.animalRar.getNume(),this.animalRar.getNumeleTariiOrigine());
    }

    public IngrijitorZoo getAngajatulLunii()
    {
        return new IngrijitorZoo(this.angajatulLunii.getNrActiuni());
    }
}
