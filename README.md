# 🎵 Audio File Type Reader

A Java program that identifies audio file formats by analyzing binary file headers — without relying on file extensions.

## 📌 About

Most programs identify files by their extension (`.mp3`, `.wav`, etc.). This project takes a lower-level approach: it reads the raw binary content of a file and detects its format by matching **magic bytes** — unique byte sequences that every audio format embeds at the start of the file.

## 🎯 Supported Formats

| Format | Full Name |
|--------|-----------|
| `.asf` | Advanced Systems Format |
| `.avi` | Audio Video Interleave |
| `.qt` | Apple QuickTime Movie |
| `.wmv` | Windows Media Video |

## 🛠️ Built With

- **Java** — Core language
- **File I/O** — Binary file reading
- **Byte-level parsing** — Magic number detection

## 💡 How It Works

1. The program opens the file in binary mode
2. It reads the first few bytes (the file header)
3. It compares those bytes against known format signatures
4. It returns the detected file type — regardless of the file's extension

## 🚀 How to Run

```bash
# Clone the repository
git clone https://github.com/moaz-ahmed/audio-file-type-reader.git

# Navigate to the project directory
cd audio-file-type-reader

# Compile
javac AudioFileReader.java

# Run
java AudioFileReader <path-to-your-file>
```

## 📚 What I Learned

- How audio and multimedia file formats are structured at a binary level
- Low-level file I/O in Java
- How to detect file types using magic byte signatures
- Practical application of multimedia standards (ASF, AVI, QT, WMV)

---

> **Course:** Introduction to Multimedia — Alexandria University
