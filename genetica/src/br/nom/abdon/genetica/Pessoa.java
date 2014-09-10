package br.nom.abdon.genetica;

/**
 *
 * @author Bruno
 */
public abstract class Pessoa {
    private final boolean taDoente;

    public Pessoa(boolean taDoente){
        this.taDoente = taDoente;
    }
    
    public boolean sobrevive(){
        return taDoente ? Deus.randomBoolean(5) : true;
    }
    
    public boolean taDoente() {
        return taDoente;
    }
    
    
}
