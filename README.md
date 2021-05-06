Mise a jour:

    1/ Dans le terminal linux exécuter la commande:
        crontab -e
    2/ une sorte de ficher va apparaitre dans le terminal  allez a la fin de ce fichier et rajouter une de ces deux lignes selon votre choix :
        * pour une mise chaque jour a 00H  écrivez cette commande :
            0 0 * * * (GET http://localhost:8080/TrainLineSNCF/Update >> home/.../miseajour.txt)
        Ou il faut remplcez "home/..." par le chemin de votre choix. 

        * pour tester rapidement la mise chaque jour ecrivez cette commande qui mettra a jour la base chaque deux minutes  :
            */2 * * * * (GET http://localhost:8080/TrainLineSNCF/Update >> home/.../miseajour.txt)
        Ou il faut remplcez "home/..." par le chemin de votre choix. 

    4/ cliquez sur ctrl+x
    5/ appuyez sur y
    6/ appuyez sur la touche Entrée
    7/Ensuite vous pouvez consulter le ficher miseajour.txt dans le répértoire que vous avez choisi.


