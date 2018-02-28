#ifndef NOCANDIDATO_H_INCLUDED
#define NOCANDIDATO_H_INCLUDED
#include "Candidato.h"

class NoCandidato {
public:
    Candidato *conteudo;
    NoCandidato *next;

    NoCandidato(Candidato *c, NoCandidato *prox){
        this->conteudo = c;
        this->next = prox;
    }

    string toString(){
        stringstream stream;
        stream << this->conteudo->toString() << " -> ";
        return stream.str();
    }
};

#endif // NOCANDIDATO_H_INCLUDED
