# AI Course Problems Repository

This repository contains a set of problems and examples related to Artificial Intelligence (AI) concepts, focusing on game theory, search algorithms, and logic programming. It is divided into two main folders:

1. **PrologExamples**: Contains various AI problems implemented in Prolog, showcasing different aspects of logic programming, such as reasoning, inference, and solving puzzles.
2. **GameTheoryProblems**: Includes several game theory problems, algorithms, and AI-based implementations, such as A* algorithm, Cryptarithm solver, and Tic-Tac-Toe game.

## Contents

- **PrologExamples**:
  - Implementations of basic and advanced problems using Prolog, such as:
    - Solving puzzles (e.g., Cryptarithms)
    - Logical inferences
    - Expert systems

- **GameTheoryProblems**:
  - AI algorithms for solving common game theory and problem-solving scenarios, including:
    - **A* Search / Heuristic Search**: A popular algorithm for pathfinding and graph traversal.
    - **Cryptarithm Solver**: A puzzle-solving algorithm where digits are replaced by letters.
    - **Tic-Tac-Toe**: Implementation of the Tic-Tac-Toe game with AI playing the game using minimax or other algorithms.
    - **Greedy-First-Search / (BFS/DFS)**: Simple graph traversal algorithms. 

## Setting up Prolog

To run the Prolog examples in the repository, you need to set up a Prolog environment. Here is a simple guide to get started:

### 1. Install SWI-Prolog
SWI-Prolog is a popular open-source Prolog implementation.

- **Windows**:
  1. Go to [SWI-Prolog download page](https://www.swi-prolog.org/Download.html).
  2. Download the installer for Windows and follow the installation instructions.

- **Linux (Ubuntu/Debian)**:
  ```bash
  sudo apt-get update
  sudo apt-get install swi-prolog
  ```

- **MacOS**:
  Use Homebrew to install SWI-Prolog:
  ```bash
  brew install swi-prolog
  ```

### 2. Verify Installation
After installation, open a terminal (or command prompt on Windows) and type:
```bash
swipl
```
You should see the SWI-Prolog interactive shell, indicating that the setup was successful.

### 3. Running Prolog Files
1. Navigate to the **PrologExamples** folder where the `.pro` files are located.
2. Launch SWI-Prolog by typing `swipl` in your terminal/command prompt.
3. Load the Prolog file by typing:
   ```prolog
   ?- consult("<filename>.pro").
   ```
   Replace `filename` with the name of the Prolog file you wish to run (e.g., `?- [puzzle_solver].`).
   
4. Run queries or test the examples as specified in the individual files.
