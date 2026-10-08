## Exercice 1

**Violation OCP**  
Il y a une violation du principe dans la classe AlertSevice, et si on veux ajouter un nouveaux type de CommunicationChannel on doivrá forcement modifier la classe AlertService

**Violation ISP**  
Il y a une violation du principe a l'interface CommunicationChannel, et les classes SMSChannel, SirenChannel et PaperChannel doiven implementer des methodes qui ne peuvent pas être utilisées par ces classes.

## Exercice 2

**Patron de conception a utiliser:** *Factory Pattern*  

Le patron fabrique permets de créer differents types de document cependant du type de createur qu'on utilise, en ce cas cependent du type de rapport. En plus il permets d'ajouter des nouveaux types de documents sans modifier les classes déjà implementées.