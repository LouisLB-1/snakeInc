## Questions sur la POO

Une classe définit un type d'objet avec des attributs et des méthodes qui lui sont associés.

On la définit comme suit dans le fichier snake : 

    public class Snake {}

Les primitives types sont les classes existantes de base dans Java comme int, string,...

L'encapsulation est une pratique de POO qui consiste à regrouper les données et les méthodes 
qui opérent sur une seule unité en un seul objet classe

La propriété est un attribut d'une classe

    public class Snake {

    private final ArrayList<Cell> body;
    private final AppleEatenListener onAppleEatenListener;
    private final Grid grid;

Les getters et les setters sont des méthodes de classe qui permettent d'accéder aux attributs
d'une classe pour les getters et de les modifier pour les setters

Le mot clé final permet de définir un variable comme non-mutable

Dans une classe, il faut absolument inclure un constructeur qui permet de créer
des instances appartenant à cette classe. Il est souvent recommandé d'inclure aussi
un compteur d'instance pour éviter la fuite de donnée.

    public Snake(AppleEatenListener listener, Grid grid) {
        this.body = new ArrayList<>();
        this.onAppleEatenListener = listener;
        this.grid = grid;
        Cell head = grid.getTile(GameParams.SNAKE_DEFAULT_X, GameParams.SNAKE_DEFAULT_Y);
        head.addSnake(this);
        body.add(head);
    }

Le mot clé static permet de faire en sorte qu'un objet soit unique est partagé par
tous les membres d'une classe et non à une instance. Ceci permet d'économiser de l'espace.

La composition est un principe qui décrit les liens entre deux classes

L'héritage est un principe qui consiste à créer une classe qui est la fille d'une autre classe.
Cette dernière héritera des attributs et méthodes de la classe mère et pourra être traité comme
un objet de la classe mère.

Une interface est un classe abstraite qui permet de relier plusieurs autres classes partageant des
propriétés similaires. Elle pourrait s'apparenter à une classe mère qui pourrait uniquement avoir des
méthodes.

Le polymorphisme vient de l'héritage, comme dit plus haut, un objet peutêtre traité comme un objet de sa 
classe mère.

Le type static est le type donné à une variable lors de sa déclaration alors que le type dynamique peut lui
changer au cours de programme.

Ex : 

        Animal an= new Cat();
        an= new Animal();

Ici le type static de an est animal mais son type dynamique passe de Cat à Animal

La separation of concerns est un principe de programmation qui consiste à séparer un programme en
différentes parties/fichiers afin de rendre sa lecture plus simple

Une collection est un objet qui permet de gérer un ensmble d'autres objets. Par exemple les list sont un 
exemple de collections.

Les exceptions sont un type d'objet permettant de gérer les erreurs dans un programme.

Une interface fonctionnelle est une interface qui ne contien qu'une seule métode abstraite.

    public interface AppleEatenListener {
    void onAppleEaten(Apple apple, Cell cell);
    }

Lambda permet de définir une méthode à la voléé, une méthode qui n'a souvent pas besoin d'être utilisé ailleurs.

Lombok est une librairie qui permet de faciliter la création de classes en automatisant la création des getters, setters et builders.