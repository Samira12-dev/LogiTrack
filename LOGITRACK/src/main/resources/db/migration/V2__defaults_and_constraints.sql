ALTER TABLE commande
    ADD CONSTRAINT fk_commande_client
        FOREIGN KEY (client_id) REFERENCES client (id);

ALTER TABLE ligne_commande
    ADD CONSTRAINT fk_ligne_produit
        FOREIGN KEY (produit_id) REFERENCES produit (id);

ALTER TABLE ligne_commande
    ADD CONSTRAINT fk_ligne_commande
        FOREIGN KEY (commande_id) REFERENCES commande (id);