Voici les instructions pour tester notre application web :
    1/ Tout d'abord, il faut importer la base de données 'trainbase'  dans phpmyadmin
    2/ Ensuite ouvrir le dossier 'TrainLineSNCF' avec eclipse.
    3/ Lancer le serveur sous eclipse, clique droit puis 'run as' puis 'Run on Server'
    4/ Importer le projet react sur vscode (par exemple)
    5/ Lancer la commande 'npm install'
    6/ Lancer la commande 'npm audit fix' (si necessaire)
    7/ Lancer la commande 'npm start' 
    8/ Sur le navigateur ecrire sur la bare de recherche : http://localhost:3000/  (si nécessaire)


Voici les instructions pour mettre a jour notre base:

    1/ Dans le terminal linux exécuter la commande:
        crontab -e
    2/ une sorte de ficher va apparaitre dans le terminal  allez a la fin de ce fichier et rajouter une de ces deux lignes selon votre choix :
        * pour une mise chaque jour a 00H  écrivez cette commande :
            0 0 * * * (GET http://localhost:8080/TrainLineSNCF/Update >> home/.../miseajour.txt)
        Ou il faut remplcez "home/..." par le chemin de votre choix. 

        * pour tester rapidement la mise chaque jour ecrivez cette commande qui mettra a jour la base chaque deux minutes  :
            */2 * * * * (GET http://localhost:8080/TrainLineSNCF/Update >> home/.../miseajour.txt)
        Ou il faut remplcez "home/..." par le chemin de votre choix. 

    4/ Cliquez sur ctrl+x
    5/ Appuyez sur y
    6/ Appuyez sur la touche Entrée
    7/ Vous pouvez consulter le ficher miseajour.txt dans le répértoire que vous avez choisi.
