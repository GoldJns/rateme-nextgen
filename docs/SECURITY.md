# Security Policy

## Supported Versions

Use this section to tell people about which versions of your project are
currently being supported with security updates.

| Version | Supported          |
| ------- | ------------------ |
| 5.1.x   | :white_check_mark: |
| 5.0.x   | :x:                |
| 4.0.x   | :white_check_mark: |
| < 4.0   | :x:                |

## Reporting a Vulnerability

You can navigate to `Issues` there is a template called `Report a vulnerability`.

## K8s secrets

Kubernetes secrets are handled via `external-secrets-plugin`. This plugin is installed with helm.
Ressource type is ExternalSecret.