#!/usr/bin/env bash
set -euo pipefail

# Format Java source using Maven fmt-maven-plugin
# Prereqs: Maven installed

SCRIPT_DIR="$(cd "$(dirname "$0")" && pwd)"
REPO_ROOT="$(cd "$SCRIPT_DIR/.." && pwd)"
cd "$REPO_ROOT"

echo "Running formatter (google-java-format via fmt-maven-plugin)..."
mvn -q com.coveo:fmt-maven-plugin:format

echo "Formatting complete. To verify no further changes are needed, run:"
echo "  git diff --stat"
