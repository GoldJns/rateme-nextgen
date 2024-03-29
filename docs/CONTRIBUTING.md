
# Guidelines

Sticking with the rules has a purpose! Release notes e.g. only work correct when guidelines are respected.

## Branches

When creating branches, please use the following naming convention:

- Feature: feature/your-feature-name
- Refactor: refactor/your-refactor-name
- Bug Fix: fix/issue-number-descriptive-name
- Chore: chore/your-chore-description


## Conventional Commits
Please use conventional commits for your contributions. Follow the format:

- Feature: feat: add new feature
- Refactor: refactor: improve existing code
- Bug Fix: fix: resolve issue with XYZ
- Chore: chore: update dependencies   

## Code Conventions

- Use `PascalCase` for Classes/ Filenames
- If possible use  `kebab-case` (e.g k8s object names)
- other stuff(like java objects etc.) should be named with `camelCase`
- Use short descriptive Names

## Rules
- Never commit directly to main, use pull requests
- Fix build problems locally before pushing, this will result in fewer pipeline runs