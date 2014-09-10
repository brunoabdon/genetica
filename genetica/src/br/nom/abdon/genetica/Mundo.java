/*
 * Copyright (C) 2014 Bruno
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package br.nom.abdon.genetica;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author Bruno
 */
public class Mundo {
    
    private static final int quantidadeInicial = 1000;
    private static final int fertilidade = 3;
    
    public void gira(int numeroDeGeracoes){
        //primeira geracao
        Set<Macho> machos = new HashSet<>(quantidadeInicial/2);
        Set<Femea> femeas = new HashSet<>(quantidadeInicial/2);
        
        for (int i = 0; i < quantidadeInicial/2; i++) {
            machos.add(new Macho(Deus.randomBoolean(99)));
            femeas.add(new Femea(Deus.randomBoolean(99)));
        }
        
        Set<Macho> machosNovaGeracao = machos;
        Set<Femea> femeasNovaGeracao = femeas;
        
        for (int i = 0; 
                i < numeroDeGeracoes 
                && !machosNovaGeracao.isEmpty()
                && !femeasNovaGeracao.isEmpty();
            i++) {
        
            imprimeRelatorioDaGeracao(machosNovaGeracao,femeasNovaGeracao);
            machos = sobreviventes(machosNovaGeracao);
            femeas = sobreviventes(femeasNovaGeracao);

            machosNovaGeracao = new HashSet<>();
            femeasNovaGeracao = new HashSet<>();
            
            Iterator<Macho> filaDeMacho = machos.iterator();
            Iterator<Femea> filaDeFemas = femeas.iterator();
            
            while(filaDeMacho.hasNext() && filaDeFemas.hasNext()){
                final Femea femea = filaDeFemas.next();
                final Macho macho = filaDeMacho.next();

                int quantosFilhos = Deus.randomBoolean(85) ? 2 :3;
                
                for (int j = 0; j < quantosFilhos; j++) {
                    procriar(femea, macho, machosNovaGeracao, femeasNovaGeracao);
                }
            }
        }
    }

    private void procriar(final Femea femea, final Macho macho, Set<Macho> machosNovaGeracao, Set<Femea> femeasNovaGeracao) {
        Pessoa filho = femea.reproduz(macho);
        
        if(filho instanceof Macho){
            machosNovaGeracao.add((Macho)filho);
        } else {
            femeasNovaGeracao.add((Femea)filho);
        }
    }
    
    private <X extends Pessoa> Set<X> sobreviventes(Set<X> pessoas){
        return 
            pessoas
                .parallelStream()
                .filter(Pessoa::sobrevive)
                .collect(Collectors.toCollection(HashSet::new));
    }

    private void imprimeRelatorioDaGeracao(Set<Macho> machosNovaGeracao, Set<Femea> femeasNovaGeracao) {
        System.out.format("%d machos (%d doentes) x %d femeas (%d doentes)\n",
            machosNovaGeracao.size(),
            machosNovaGeracao.stream().filter(Pessoa::taDoente).count(),
            femeasNovaGeracao.size(),
            femeasNovaGeracao.stream().filter(Pessoa::taDoente).count()
        );
    }
    
    
    public static void main(String[] args) {
        Mundo mundo = new Mundo();
        mundo.gira(100);
    }
    
    
}
