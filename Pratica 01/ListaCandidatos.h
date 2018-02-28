#ifndef LISTACANDIDATOS_H_INCLUDED
#define LISTACANDIDATOS_H_INCLUDED

#include <iostream>
#include <fstream>
#include "Nocandidato.h"
#include "Candidato.h"

class ListaCandidatos{
public:
    NoCandidato *head;
    int tam;

    ListaCandidatos(){
        this->head = NULL;
        tam = 0;
    }

    ListaCandidatos(const char arq[]){
        this->head = NULL;
        tam = 0;
        ifstream fcin(arq);
        Candidato *c;
        string dados;
        getline(fcin,dados);
        //cout << "nome da regiao: " << dados << endl;

        while(getline(fcin,dados)){
            c = new Candidato(dados);
            this->adicioneComoHead(c);
            //cout << "criacao do candidato: " << c->toString() << endl;
        }
    }

    bool estaVazia(){
        return head == NULL;
    }

    void adicioneComoHead(Candidato *c){
        this->head = new NoCandidato(c,head);
        tam++;
    }

    int tamanho(){
        return tam;
    }

    string toString(){
        stringstream stream;
        if(this->tamanho() == 0) return "0";
        NoCandidato *aux = head;
        for(int i=0; i<this->tamanho(); i++){
            stream << aux->toString();
            aux = aux->next;
        }
        //cout << aux->conteudo->nome << endl;
        stream << aux;
        return stream.str();
    }

    bool remove(string nome, string sobrenome){
        NoCandidato *atual = head;
        NoCandidato *ant = NULL;

        while(atual!=NULL){
            if(atual->conteudo->igual(nome, sobrenome)){
                if(ant == NULL){
                    head = atual->next;
                    delete atual;
                    tam--;
                    return true;
                }
                else{
                    NoCandidato *proxCandidato = atual->next;
                    delete atual;
                    atual = proxCandidato;
                    ant->next = atual;
                    tam--;
                    return true;
                }
            }
            ant = atual;
            atual = atual->next;
        }
        return false;
    }

    void filtrarCandidatos(int nota){
        NoCandidato *it = head;

        while(it->next != NULL && tam > 0){
            if(it->conteudo->nota < nota){
                head = head->next;
                delete it;
                tam--;
            }
            else
            {
                if(it->conteudo->nota < nota){
                    NoCandidato *proxCandidato = it->next;
                    delete it;
                    it = proxCandidato;
                    tam--;
                }
                else
                {
                    it = it->next;
                }
            }
        }
    }
};


#endif // LISTACANDIDATOS_H_INCLUDED
