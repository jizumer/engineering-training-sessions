# How to use keys for authentication and signing commits

In git, there are two main usages for asymmetric cryptographic keys.

1. Authentication: Will be used for the SSL communication with git when cloning, pushing, or pulling from/to a repository.
2. Signing commits: Locally, when committing changes, we can sign them to guarantee that are not impersonated.


# GPG keys

You can obtain the GPG private key LOCAL ID executing the following command:

```
$ gpg --list-secret-keys --keyid-format=long
```


Don't forget to upload the public GPG key to Github
GPG keychain > export (don't include secret key) > copy > paste in Github

```
-----BEGIN PGP PUBLIC KEY BLOCK-----
xxxx
-----END PGP PUBLIC KEY BLOCK-----
```