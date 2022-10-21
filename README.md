# ValoCB
![coverage](.github/badges/jacoco.svg)
![branches coverage](.github/badges/branches.svg)

Nous souhaitons développer une application qui s’appelle ValoCB de reporting pour agréger les
prix des produits détenus par des clients dans différents portefeuilles.
Un portefeuille contient plusieurs produits. Un produit peut appartenir à un seul portefeuille. 

*Ex: PTF2 contient les produits X1 et X2*

Un produit peut avoir un ou plusieurs underlyings.

*EX : Le produit P1 contient 3 underlyings U11, U12 et U13*

Chaque client détient plusieurs produits qui peuvent être dans différents portefeuilles. Ex : Le client C2 détient les produits
- 40P1 et 100P3 qui sont dans le
- PTF1 - 20 X2 qui sont dans le portefeuille PTF2
  L’objectif de l’application est de calculer
  - Le prix d’un portefeuille en EUR
  - Le prix des produits par client en EUR

On aura en input 3 fichiers csv : 
- Prices.csv
- Product.csv 
- Forex.csv

Ces fichiers seront insérés dans les ressources du projets (`src/main/resources`) pour faciliter le dev.
  L’application doit générer dans le dossier de votre choix 2 fichiers csv :
- Reporting-portfolio.csv : contient les prix des portefeuilles : 2 colonnes PTF et price
- Reporting-client.csv : contient les capitaux des clients : 2 colonnes Client et capital
  
#### Techniquement 
- Java 11+ (idéalement 17)
- Couverture en test unitaire supérieure à 80% (idéalement 100%)
- Code livré sur un repo git de votre choix

- Vous êtes libres sur le choix des frameworks/librairies
  
#### Prix d’un produit en EUR
Prix (P1) = Prix (U11) x 1 + Prix (U12) x Forex (USD#EUR) + Prix (U13) x Forex (USD#EUR) = 10 + 20 x 0,5 + 30 * 0,5
  = 35


| Portfolio | Product | Underlying | Currency | Price |
|-----------|---------|------------|----------|-------|
| PTF1      | P1      | U11        | EUR      | 10    |
| PTF1      | P1      | U12        | USD      | 20    |
| PTF1      | P1      | U13        | USD      | 30    |


|  Currency | Currency | Value |
|-----------|----------|-------|
| EUR       |  USD     | 2     |
 
#### Prix d’un portefeuille en EUR

Prix portefeuille = Somme (Prix_produit x quantité) Prix_produit = Somme (prix_underlying * forex)


Prix PTF 2 = Somme ( (prix(X1) x (10 + 20 + 40) + (prix (X2) x (20 + 30 + 50 + 60)) )

| Product | Client | Quantity |
|---------|--------|----------|
| X1      | C6     | 10       |
| X1      | C1     | 20       |
| X1      | C7     | 40       |
| X2      | C7     | 30       |
| X2      | C5     | 50       |
| X2      | C8     | 60       |


#### Le prix des produits par client en EUR

Capital_client = Somme ( Prix(produit) x quantite) 

Ex : Capital(C3) = Prix (P1) x 30 + Prix (P3) x 80

| Product | Client | Quantity |
|---------|--------|----------|
| P1      | C3     | 30       |
| P3      | C3     | 80       |

