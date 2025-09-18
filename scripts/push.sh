#!/usr/bin/env bash
set -euo pipefail

# Simple helper to push current branch to GitHub.
# Usage:
#   scripts/push.sh "Your commit message here" [remote_url] [branch]
# - If remote_url is provided and no origin exists, it will be set.
# - Default branch is "main" if not provided.

commit_msg=${1:-}
remote_url=${2:-}
branch=${3:-main}

if ! command -v git >/dev/null 2>&1; then
  echo "Error: git is not installed or not on PATH." >&2
  exit 1
fi

# Ensure we're in a git repo
if ! git rev-parse --is-inside-work-tree >/dev/null 2>&1; then
  echo "Error: Not inside a git repository. Run 'git init' first." >&2
  exit 1
fi

# Show status
git status --short

# Ensure a commit message
if [ -z "$commit_msg" ]; then
  echo "Usage: scripts/push.sh \"Your commit message\" [remote_url] [branch]" >&2
  exit 1
fi

# Add files respecting .gitignore
git add -A

# Commit (no-op if nothing to commit)
if git diff --cached --quiet; then
  echo "No changes to commit. Skipping commit step."
else
  git commit -m "$commit_msg"
fi

# Check/set origin
if git remote get-url origin >/dev/null 2>&1; then
  echo "Using existing remote 'origin' -> $(git remote get-url origin)"
else
  if [ -n "$remote_url" ]; then
    echo "Setting remote 'origin' to $remote_url"
    git remote add origin "$remote_url"
  else
    cat >&2 <<EOF
Error: No remote 'origin' configured and no remote_url provided.
Provide your GitHub repo URL, e.g.:
  scripts/push.sh "Initial commit" https://github.com/<user>/<repo>.git [branch]
EOF
    exit 1
  fi
fi

# Ensure branch exists
current_branch=$(git rev-parse --abbrev-ref HEAD)
if [ "$current_branch" = "HEAD" ]; then
  # Detached HEAD; create branch if needed
  git checkout -B "$branch"
  current_branch="$branch"
fi

# Rename/align branch if requested branch differs
if [ "$current_branch" != "$branch" ]; then
  echo "Switching to branch '$branch' (current: $current_branch)"
  git checkout -B "$branch"
fi

# Push
set +e
git push -u origin "$branch"
rc=$?
set -e

if [ $rc -ne 0 ]; then
  cat >&2 <<EOF
Push failed. Common causes:
- The remote repository does not exist or you lack permissions.
- You need to authenticate (use SSH or HTTPS with a token).
- The default branch on remote has protections.

Try:
  git remote -v
  git status
  git branch -vv
  git push -u origin $branch
EOF
  exit $rc
fi

echo "Push successful to $(git remote get-url origin) [$branch]"