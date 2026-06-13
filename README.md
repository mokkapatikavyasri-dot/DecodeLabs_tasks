# 🖥️ DecodeLabs Java Tasks

Welcome to my **DecodeLabs Java Programming Repository**!
This repository contains four hands-on Java console applications built to strengthen core Java concepts — from game logic and OOP to data formatting and currency math.

> 👩‍💻 **Author:** Mokkapati KavyaSri &nbsp;|&nbsp; 📅 **Batch:** DecodeLabs 2026
> 🔗 **GitHub:** [@mokkapatikavyasri-dot](https://github.com/mokkapatikavyasri-dot)

---

## 📂 Repository Structure

```
DecodeLabs_tasks/
├── Task_1_KavyaSri/   → Number Guessing Game
├── Task_2_KavyaSri/   → Student Grade Calculator
├── Task_3_KavyaSri/   → ATM Banking System
└── Task_4_KavyaSri/   → Currency Converter
```

---

## 📝 Task Overviews

---

### 🔹 Task 1 — Number Guessing Game

**File:** `DecodeLabs_Java_P1.java`

**Description:**
An interactive multi-round console game where the player guesses a randomly generated number between 1 and 100. Each round gives 7 attempts with "Too High / Too Low" hints. A scoring system rewards faster guesses, and the player can keep playing new rounds until they choose to stop.

**Key Features:**
- Random number generation using `java.util.Random`
- Multi-round gameplay using a `do-while` loop
- Score system — fewer attempts = more points (score = `(maxAttempts - attempts + 1) × 10`)
- Input validation using `try-catch` to handle non-numeric entries
- Remaining attempts counter displayed after each wrong guess

**Concepts Covered:** `do-while` loop, `while` loop, `try-catch`, `Random`, `Scanner`, accumulator variables

**Technologies:** Java SE, `java.util.Scanner`, `java.util.Random`

---

### 🔹 Task 2 — Student Grade Calculator

**File:** `DecodeLabs_Java_P2.java`

**Description:**
A console application that accepts a student's name, number of subjects, subject names, and marks (0–100) as input. It calculates total marks, average percentage, assigns a letter grade (A+ to F), and prints a neatly formatted report card using `printf`.

**Key Features:**
- Dynamic subject entry — user chooses how many subjects to enter
- Input validation — marks must be between 0 and 100
- Grade logic ladder: A+ (≥90), A (≥80), B (≥70), C (≥60), D (≥50), F (<50)
- `(double)` type casting to prevent integer division truncation
- Formatted report card output using `printf` with column alignment
- PASS / FAIL result with remarks

**Concepts Covered:** Arrays, `for` loops, `while` loops, type casting, `printf` formatting, conditional logic

**Technologies:** Java SE, `java.util.Scanner`

---

### 🔹 Task 3 — ATM Banking System

**File:** `DecodeLabs_Java_P3.java`

**Description:**
A fully object-oriented ATM simulation split across two classes — `BankAccount` (business logic layer) and `ATM` (user interface layer). The system supports PIN authentication, balance enquiry, cash deposit, cash withdrawal, and transaction history — all with robust input validation.

**Key Features:**
- PIN authentication with up to 3 attempts before card block
- `BankAccount` class with private fields and encapsulated methods (deposit, withdraw, validate)
- Transaction history stored in an `ArrayList` and displayed with indexing
- Overdraft protection — withdrawal rejected if amount exceeds balance
- Input validated using `hasNextInt()` / `hasNextDouble()` (peek before consume — no crashes)
- Clean UI with box-drawing characters for menus and section headers

**Concepts Covered:** OOP (classes, encapsulation, constructors, getters), `ArrayList`, `switch` expressions, input validation, separation of concerns

**Technologies:** Java SE, `java.util.Scanner`, `java.util.ArrayList`

---

### 🔹 Task 4 — Currency Converter

**File:** `DecodeLabs_Java_P4.java`

**Description:**
A console-based currency converter supporting 10 world currencies. The user selects a base currency, a target currency, and enters an amount — the app converts the value using USD as the pivot currency and displays the result with exchange rates. Multiple conversions can be done in one session.

**Supported Currencies:** USD, INR, EUR, GBP, JPY, AUD, CAD, SGD, AED, CNY

**Key Features:**
- 10 currencies with live-style exchange rates (2026 rates)
- Two-step conversion: `Amount → USD → Target Currency`
- Displays both forward and reverse exchange rates
- Input validation for currency selection (1–10) and amount (must be > 0)
- Repeat-conversion loop using `do-while`
- Formatted output with currency symbols (₹, €, £, ¥, etc.)

**Concepts Covered:** Parallel arrays, static methods, `do-while` loop, `printf` formatting, type conversion math

**Technologies:** Java SE, `java.util.Scanner`

---

## 🚀 Getting Started

### Prerequisites

Make sure you have the following installed:

- **Java JDK 11 or above** — [Download here](https://www.oracle.com/java/technologies/downloads/)
- **Git** — [Download here](https://git-scm.com/)
- Any Java IDE (IntelliJ IDEA, Eclipse, VS Code) — or use the terminal

### Installation & Setup

**1. Clone the repository:**
```bash
git clone https://github.com/mokkapatikavyasri-dot/DecodeLabs_tasks.git
cd DecodeLabs_tasks
```

**2. Navigate to the task folder:**
```bash
cd Task_1_KavyaSri
```

**3. Compile the Java file:**
```bash
javac DecodeLabs_Java_P1.java
```

**4. Run the program:**
```bash
java DecodeLabs_Java_P1
```

> Repeat steps 2–4 for Tasks 2, 3, and 4 by replacing the folder name and filename accordingly.

---

## 🛠️ Technologies Used

| Technology | Purpose |
|---|---|
| Java SE (JDK 11+) | Core programming language |
| `java.util.Scanner` | Console input handling |
| `java.util.Random` | Random number generation (Task 1) |
| `java.util.ArrayList` | Dynamic transaction history (Task 3) |
| OOP (Classes & Encapsulation) | ATM system design (Task 3) |

---

## 📌 Key Java Concepts Demonstrated

- Control flow: `if-else`, `switch`, `while`, `do-while`, `for` loops
- Input validation with `try-catch` and `hasNextInt()` / `hasNextDouble()`
- Object-Oriented Programming: classes, encapsulation, constructors, getters
- Arrays and `ArrayList`
- Type casting and `printf` formatting
- Modular design with static helper methods

---

*Powered by DecodeLabs — Kavyasri 2026* 🚀
