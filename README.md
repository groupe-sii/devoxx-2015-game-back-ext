# devoxx-2015-game-back-ext
Extension module for the Game development challenge (devoxx 2015)

# 1. Prerequisites

* [git](http://git-scm.com/downloads)
* [Java JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
* [maven 3.x](https://maven.apache.org/download.cgi)
* A JAVA editor


# 2. Grab the code

## 2.1 Get the sources

Get the code using your git client:
```
~/git> git clone https://github.com/groupe-sii/devoxx-2015-game-back-ext
```

## 2.2 Generate the JAR file

You just need to execute the following command into the source folder (devoxx-2015-game-back-ext):
```
~/git> cd devoxx-game-back-ext
~/git/devoxx-game-back-ext> mvn clean install
```

## 2.3 Run the extensions

To run the extensions, you need to [follow these steps if not already done](https://github.com/groupe-sii/devoxx-2015-game-back).

# 3 Start coding extensions

To help you to handle our extensions API, we provide some documentation and obviously some javadoc.


## 3.1 Documentation

### Enemies

See the [Enemy](src/main/resources/docs/Enemy) documentation


### Animations

See the [Animations](src/main/resources/docs/Animation) documentation


### Rules

See the [Rules](src/main/resources/docs/Rule) documentation


## 3.2 Javadoc
* Main module and game API : http://game.javadoc.devoxx.sii.fr/core/
* Game extensions API : http://game.javadoc.devoxx.sii.fr/extensions/


# 4. Push your extensions

## 4.1 Ask access

Before you can push your changes, you need to ask for write access to the repository. First, you need to create a Github account. Then, you can contact Aurélien Baudet on the SII stand to send you an invitation.


## 4.2 Pull and push

Once you have developed your extension and you want to view it online, you need to push your changes to the remote git. But before doing this, ensure that you have the latest sources:
```
git pull
```

The merge should be done automatically. If you have conflicts, you can follow these guides to manually merge:
- [Command line merge](https://help.github.com/articles/resolving-a-merge-conflict-from-the-command-line/)
- [Using git mergetool](http://www.gitguys.com/topics/merging-with-a-gui/)

 
Now you can push to remote git server:
```
git push origin master
```

Our Jenkins will automatically build your extensions and deploy them on the server. You will soon see your extensions in action.

/!\ Please, this game has been done for making devoxx even funnier so do not break code of other participants

