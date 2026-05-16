import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

/*  1: Run the Programme
    2: Input the File Location and the total audio file name in the terminal
    3: press Enter
    4: in the output he shows the type of the audio and the Header.
EX: C:\\Users\\Mega Store\\Downloads\\freesound_community-cafe-noise-32940.mp3
(output)-> Detected Format: MP3
            Header Data:
FF FB D0 C4 00 00 2A D6 0B 1A D5 9C 00 06 85 C2 
24 D7 3B A0 02 04 00 00 CC 60 8E 69 BE 73 DA 76 
CE 6C 82 30 51 BD 41 D5 00 43 88 80 18 03 0B 48 
75 07 75 E0 04 E4 2E 41 94 60 21 97 8D 31 D0 96 
-------------------------------------------------------------------------------
Ex: C:\\Users\\Mega Store\\Downloads\\sound.wav
(output)-> Detected Format: WAV

Header Data:
52 49 46 46 24 84 03 00 57 41 56 45 66 6D 74 20 
10 00 00 00 01 00 02 00 44 AC 00 00 10 B1 02 00 
04 00 10 00 64 61 74 61 00 84 03 00 00 00 00 00 
00 00 00 00 00 00 FF FF 00 00 01 00 01 00 00 00 


WAV Information:
Channels       : 2
Sample Rate    : 44100
Bits Per Sample: 16
           
*/

public class AudioFileReader {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter audio file path: ");

        String path = scanner.nextLine();

        File file = new File(path);

        if (!file.exists()) {
            System.out.println("Error: File not found.");
            return;
        }

        if (!file.isFile()) {
            System.out.println("Error: Invalid file.");
            return;
        }

        try {

            FileInputStream fis = new FileInputStream(file);

            
            byte[] header = new byte[64];

            int bytesRead = fis.read(header);

            if (bytesRead <= 0) {
                System.out.println("Error reading file.");
                return;
            }

            String format = "UNKNOWN";

        
            if (header[0] == 'R' &&
                header[1] == 'I' &&
                header[2] == 'F' &&
                header[3] == 'F' &&

                header[8] == 'W' &&
                header[9] == 'A' &&
                header[10] == 'V' &&
                header[11] == 'E') {

                format = "WAV";
            }

            
            else if (

                
                (header[0] == 'I' &&
                 header[1] == 'D' &&
                 header[2] == '3')

                 ||

               
                ((header[0] & 0xFF) == 0xFF &&
                 ((header[1] & 0xE0) == 0xE0))

            ) {

                format = "MP3";
            }

            
            else if (header[0] == 'O' &&
                     header[1] == 'g' &&
                     header[2] == 'g' &&
                     header[3] == 'S') {

                format = "OGG";
            }

            
            else if (header[0] == 'f' &&
                     header[1] == 'L' &&
                     header[2] == 'a' &&
                     header[3] == 'C') {

                format = "FLAC";
            }

            
            else if (

                
                ((header[0] & 0xFF) == 0xFF &&
                (((header[1] & 0xFF) == 0xF1) ||
                 ((header[1] & 0xFF) == 0xF9)))

                 ||

                
                (header[4] == 'f' &&
                 header[5] == 't' &&
                 header[6] == 'y' &&
                 header[7] == 'p')

            ) {

                format = "AAC";
            }

            
            else if ((header[0] & 0xFF) == 0x30 &&
                     (header[1] & 0xFF) == 0x26 &&
                     (header[2] & 0xFF) == 0xB2 &&
                     (header[3] & 0xFF) == 0x75) {

                format = "WMA";
            }

            
            else {

                String lower = file.getName().toLowerCase();

                if (lower.endsWith(".wav")) {
                    format = "WAV";
                }

                else if (lower.endsWith(".mp3")) {
                    format = "MP3";
                }

                else if (lower.endsWith(".ogg")) {
                    format = "OGG";
                }

                else if (lower.endsWith(".flac")) {
                    format = "FLAC";
                }

                else if (lower.endsWith(".aac") ||
                         lower.endsWith(".m4a")) {

                    format = "AAC";
                }

                else if (lower.endsWith(".wma")) {
                    format = "WMA";
                }
            }

            

            System.out.println();
            System.out.println("Detected Format: " + format);

            System.out.println("\nHeader Data:");

            for (int i = 0; i < bytesRead; i++) {

                System.out.print(
                    String.format("%02X ", header[i])
                );

                if ((i + 1) % 16 == 0) {
                    System.out.println();
                }
            }

            

            if (format.equals("WAV")) {

                int channels =
                        ((header[23] & 0xFF) << 8) |
                        (header[22] & 0xFF);

                int sampleRate =
                        (header[24] & 0xFF) |
                        ((header[25] & 0xFF) << 8) |
                        ((header[26] & 0xFF) << 16) |
                        ((header[27] & 0xFF) << 24);

                int bitsPerSample =
                        ((header[35] & 0xFF) << 8) |
                        (header[34] & 0xFF);

                System.out.println("\n\nWAV Information:");
                System.out.println("Channels       : " + channels);
                System.out.println("Sample Rate    : " + sampleRate);
                System.out.println("Bits Per Sample: " + bitsPerSample);
            }

            fis.close();

        }

        catch (IOException e) {

            System.out.println("Error while reading the file.");
        }

        scanner.close();
    }
}
