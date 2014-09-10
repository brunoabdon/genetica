package br.nom.abdon.genetica;

/**
 * @author Bruno
 */
public class Femea extends Pessoa{

    public Femea(boolean taDoente) {
        super(taDoente);
    }
    
    public Pessoa reproduz(Macho macho){
        boolean contaminado = 
            (this.taDoente() && macho.taDoente())
            || ((this.taDoente() || macho.taDoente()) && Deus.randomBoolean(50));
        
        return
            Deus.randomBoolean(50)
                ? new Macho(contaminado)
                : new Femea(contaminado);
    }
    
}
