# How to edit author locally before pushing


Given the following situation:

- you commit changes with the wrong email address in git, and
- your remote has a hook set up to prevent you from pushing with the wrong address or,
- you need to change the author of the commit for other reasons

Then you need to amend the author of your commit before push can succeed:

1. fix your email address in git config:
```bash
$ git config user.name "John Doe"
$ git config user.email "john.doe@....com"
$ git submodule foreach --recursive 'git config user.name "John Doe" && git config user.email "john.doe@....com"'
```
2. then do a rebase:
```bash
$ git rebase -i HEAD~1
```
> Git will now bring up your editor.

3. in the editor, mark the commit as 'edit' then save and exit the editor

> In VIM, to edit the file, you will need to press `ESC`, then `i` to start editing.
When you are done you can save by typing `ESC` and `:wq`

4. do an amend that resets the author now that your address is changed:
```
$ git commit --amend --reset-author
```
5. finish with a rebase:
```
$ git rebase --continue
```
git finally will say: `Successfully rebased and updated refs/heads/master`

## References
- [Original Gist](https://gist.github.com/B4nan/10259532)