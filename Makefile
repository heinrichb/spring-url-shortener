# Purpose:
#   - "install": Ensures dependencies are installed and up to date.
#   - "build": Compiles the project if source files have changed.
#   - "run": Runs the application.
#   - "test": Runs unit tests.
#   - "clean": Removes build artifacts.
#   - "tree": Displays the directory structure.
#   - All skip with "No changes detected, skipping X." if nothing changed.

.PHONY: install build run test clean tree

# Directories for build artifacts and stamp files
BUILD_DIR       := target
STAMPS_DIR      := $(BUILD_DIR)/.stamps

# Stamp file paths for each step
INSTALL_STAMP   := $(STAMPS_DIR)/install.stamp
BUILD_STAMP     := $(STAMPS_DIR)/build.stamp
TEST_STAMP      := $(STAMPS_DIR)/test.stamp

# Java source files
JAVA_FILES      := $(shell find src/main -type f -name '*.java')
TEST_FILES      := $(shell find src/test -type f -name '*.java')

# Reusable messages
SKIP_MSG        := No changes detected, skipping
CHANGE_MSG      := Some files changed; re-running

# -------------------------------------------------------------------------------
# install: Ensures dependencies are installed and up to date.
# -------------------------------------------------------------------------------
install:
	@mkdir -p $(STAMPS_DIR)
	@TARGET=install; \
	if [ ! -f "$(INSTALL_STAMP)" ] || [ pom.xml -nt "$(INSTALL_STAMP)" ]; then \
		echo "$(CHANGE_MSG) $$TARGET..."; \
		./mvnw clean install -DskipTests; \
		touch "$(INSTALL_STAMP)"; \
		echo "Done with installing dependencies."; \
	else \
		echo "$(SKIP_MSG) $$TARGET."; \
	fi

# -------------------------------------------------------------------------------
# build: Compiles the project if source files changed.
# Depends on install.
# -------------------------------------------------------------------------------
build: install
	@mkdir -p $(STAMPS_DIR)
	@TARGET=build; \
	if [ ! -f "$(BUILD_STAMP)" ] || [ -n "$$(find $(JAVA_FILES) -newer "$(BUILD_STAMP)" 2>/dev/null)" ]; then \
		echo "$(CHANGE_MSG) $$TARGET..."; \
		./mvnw package -DskipTests; \
		touch "$(BUILD_STAMP)"; \
		echo "Done with building."; \
	else \
		echo "$(SKIP_MSG) $$TARGET."; \
	fi

# -------------------------------------------------------------------------------
# run: Runs the application.
# Depends on build.
# -------------------------------------------------------------------------------
run: build
	@echo "Running application..."
	@./mvnw spring-boot:run

# -------------------------------------------------------------------------------
# test: Runs unit tests if source files changed.
# Depends on install.
# -------------------------------------------------------------------------------
test: install
	@mkdir -p $(STAMPS_DIR)
	@TARGET=test; \
	if [ ! -f "$(TEST_STAMP)" ] || [ -n "$$(find $(TEST_FILES) -newer "$(TEST_STAMP)" 2>/dev/null)" ]; then \
		echo "$(CHANGE_MSG) $$TARGET..."; \
		./mvnw test; \
		touch "$(TEST_STAMP)"; \
		echo "Tests completed."; \
	else \
		echo "$(SKIP_MSG) $$TARGET."; \
	fi

# -------------------------------------------------------------------------------
# clean: Removes build artifacts.
# -------------------------------------------------------------------------------
clean:
	@echo "Cleaning project..."
	@./mvnw clean
	@rm -rf $(STAMPS_DIR)
	@echo "Clean complete."

# -------------------------------------------------------------------------------
# tree: Displays directory structure.
# -------------------------------------------------------------------------------
tree:
	@if ! command -v tree >/dev/null 2>&1; then \
		echo "tree command not found. Attempting to install..."; \
		OS=$$(uname); \
		if [ "$$OS" = "Linux" ]; then \
			sudo apt-get update && sudo apt-get install -y tree; \
		elif [ "$$OS" = "Darwin" ]; then \
			brew install tree; \
		else \
			echo "Automatic installation not supported on $$OS. Please install manually."; \
			exit 1; \
		fi; \
	fi; \
	tree -n -I "target|.git"
