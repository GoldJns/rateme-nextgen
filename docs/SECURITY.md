# Security Policy

## K8s Security

Provide secrets before deploying:

 => secret with name `db-security` and key `password` should be available for every namespace.
 => secret with name `springboot-security` and key `jwt-token` should be available for every namespace.

The relevant secrets are stored securely in the Github Environments.

## Docker compose security

Create `.env` file with key `JWT_SECRET`. Assign value stored in Github Environments
Dont even think about commiting the env file!😂

## Reporting a Vulnerability

You can navigate to `Issues` there is a template called `Report a vulnerability`.

