## BookStore Application

### `Instructions before work`

* Pull all branches ` git pull --all`
* Goto master branch ` git checkout master`
* Pull updated code from master ` git pull `
* **Create New Branch** `git checkout -b "branch-name"` 
* Make sure that you are working on created branch `git branch`
* Do changes/work and make **Pull Request**


### `Branch Name Conventions`
* Use hyphens ` - `  instaed of other characters like underscore ` _ ` and stuff.
* Any code changes for a new module or use case should be done on a feature branch :  `feature/feature-name`
  <br/>
  Example :` feature/profile-screen `
* Any kind of bug fix : ` bugfix/bug-name ` 
  <br/>
  Example :` bugfix/login-bug `
* Any kind of change that should be handled immediately : ` hotfix/fix-name ` 
  <br/>
  Example :` hotfix/product-card-overflow `
* Working on new experimental idea : ` experimental/experiment-name`
  <br/>
  Example :`experimental/dark-theme-support
 `
### ` Pull Request Convensions `
* While making pull request make sure you followed above convensions.
* Please make sure to add appropriate comment while making PR.
* If you *failed to add appropriate comments and unable to follow convensions*, your PR might get cancelled/closed.
* Use appropriate text markups while adding comments, like Bold, Italic, List etc.
* Add reviewers while making PR.

### ` Commit message best practices `
* feat: (new feature for the user, not a new feature for build script)
* fix: (bug fix for the user, not a fix to a build script)
* docs: (changes to the documentation)
* style: (formatting, missing semi colons, etc; no production code change)
* refactor: (refactoring production code, eg. renaming a variable)
* test: (adding missing tests, refactoring tests; no production code change)
* chore: (updating grunt tasks etc; no production code change)
