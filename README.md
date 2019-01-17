# Roxbotix FRC 2019 Code
## Git help
### I just wanna add something
In most cases the simplest thing you will do is the following 3 commands. What this basically does is add all the files in the project directory to be staged for committing. 

Then, everything that is staged is added to a local commit. A commit is a collection of changes that is tagged by a comment describing them. Commit should be kept relatively small so that if something goes wrong they can be reverted without effecting too much other code.

Finally, the commit is pushed to `origin` on the branch specifed, usually `master`. `origin` is the remote source where the repository is held online. In our case, github.

```
git add .
git commit -m "Some message describing what you did!"
git push origin [branch name]
```

### Branches
As more people start working on the project at the same time, you should start opening your own branches for the thing you are working on. Separate branches out people to work simultaneously without effecting eachother's code. Commits can be made to a branch without effecting other branches, and then when ready to be added to the main code, they can be merged together.

To open a new branch you can use the following commands:

```
git branch [branch name]
git checkout [branch name]
```

The `git branch [name]` command will create the new branch and then `git checkout [name]` will switch to that new branch. Running just `git branch` command will list the branches that exist.