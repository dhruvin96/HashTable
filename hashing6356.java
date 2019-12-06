/* Dhruvinkumar Desai cs610 6356 prp */


import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

class lexicon6356{
    // This class stores data using hash mechanism.
    int T[]; //T is our hash table storing reference to first character of key in A.
    char A[]; // An array containing '\0' seperated string.
    int index_pointer_a; // This points to first empty index of A or say starting index of first char of new word.
    float load_factor;
    int key_in_t;
    
    public lexicon6356(){
        T = null;
        A = null;
        index_pointer_a = 1;
        key_in_t = 0;
    }
}

public class hashing6356{
    public static void main(String args[]){
        System.out.println("\nWelcome to Hashing.\n");
        
        // Checking for presence of command line variable.
        boolean COMMAND_LINE_ARGUMENT_PRESENT = false;
        if(args.length > 0){
            COMMAND_LINE_ARGUMENT_PRESENT = true;
        }
        
        // Initiating Controller instance (i.e. hashing6356 instance).
        hashing6356 controller = new hashing6356();
        // Initiating lexion handler for A and T.
        lexicon6356 L = new lexicon6356();
        // Creating file object.
        File command_file;
        
        // Control redirection based on if file is present to automatic testing.
        // else user control or manual control.
        if(COMMAND_LINE_ARGUMENT_PRESENT){
            // Automatic testing.
            System.out.println("File name received. Processing automatic testing....\n");
            try{
                command_file = new File(args[0]);
                //System.out.println(args[0]);
                controller.HashBatch(L, command_file);
                
            }
            catch(Exception err){
                System.out.println("Exception occured. Check if file not present.\n");
                String temp = Arrays.toString(err.getStackTrace());
                System.out.println(temp + "\n");
                System.out.println("Error Reported in prp_6356.txt\n");

            }
            finally{
                
            }
        }
        else{
            // Manual Control.
            System.out.println("No file found in command line.");
            System.out.println("Shifting control to manual....");
            
            
            Scanner sc = new Scanner(System.in);
            boolean EXIT_MANUAL = false;
            while(!EXIT_MANUAL){
                try{
                    System.out.println("\n\n1. For creating new Hash."
                            + "\n2. For Inserting element in Hash."
                            + "\n3. For Deleting element from Hash."
                            + "\n4. For Searching element in Hash."
                            + "\n5. For Printing Hash."
                            + "\n6. For Checking how much Hash is filled."
                            + "\n7. For Checking how much Space is left in Hash."
                            + "\n8. For performing Batch Operations."
                            + "\n9. For Exit.\n");
                    System.out.println("Enter your choice: ");
                    int choice = sc.nextInt();
                    sc.nextLine();
                    String word = null;
                    // Processing user choice.
                    switch(choice){
                        case 1:
                            // Creating Hash.
                            int m = 0;
                            System.out.print("\nPlease enter the initial size of Hash you want (IN INTEGER): ");
                            if(sc.hasNextInt()){
                                m = sc.nextInt();
                                sc.nextLine();
                            }
                            else{
                                System.out.println("\nNOT AN INTEGER NUMBER!!!\n");
                            }
                            
                            // Function call.
                            controller.HashCreate(L, m);
                            
                            System.out.println("\nHash Created.");
                            
                        break;
                        
                        case 2:
                            // Inserting Element.
                            
                            System.out.print("\nPlease enter the word you want to insert: ");
                            word = sc.nextLine();
                            
                            // Function call.
                            controller.HashInsert(L, word);
                            
                            System.out.println("\n"+word+" inserted.");
                        break;
                        
                        case 3:
                            // Deleting Element.
                            
                            System.out.print("\nPlease enter the word you want to delete: ");
                            word = sc.nextLine();
                            
                            // Function call.
                            controller.HashDelete(L, word);
                            
                            System.out.println("\n"+word+" deleted.");
                        break;
                        
                        case 4:
                            // Searching Element.
                            
                            System.out.print("\nPlease enter the word you want to delete: ");
                            word = sc.nextLine();
                            
                            // Function call.
                            controller.HashSearch(L, word);
                        break;
                        
                        case 5:
                            // Printing Hash.
                            controller.HashPrint(L);
                        break;
                        
                        case 6:
                            // Checking how full is array.
                            controller.HashFull(L);
                        break;
                        
                        case 7:
                            // Checking how much space array has left.
                            controller.HashEmpty(L);
                        break;
                        
                        case 8:
                            // Performing Batch operations.
                            System.out.println("\n Please make sure that you strictly follow syntax for batch operation file. More is present in prp_6356.txt.");
                            System.out.println("If you don`t follow proper syntax than you will not find expected outcome.");
                            
                            System.out.print("\nPlease enter the path to text file: ");
                            
                            if(sc.hasNextLine()){
                                command_file = new File(sc.nextLine());
                                // Function call.
                                controller.HashBatch(L, command_file);
                            }
                            else{
                                System.out.println("File name/path not given.");
                            }
                            
                        break;
                        
                        case 9:
                            // Exit.
                            EXIT_MANUAL = true;
                        break;
                        
                        default:
                            System.out.println("\n'"+choice+"' is not a valid choice.\n");
                        break;
                    }
                }
                
                catch(Exception manual_err){
                    System.out.println("Exception occured. Check if you selected right and proper choice.\n");
                    String temp = Arrays.toString(manual_err.getStackTrace());
                    System.out.println(temp + "\n");
                    System.out.println("Error Reported in prp_6356.txt\n");
                }
            }
            
            //System.out.println("Manual Control Coming soon....");
        }
        System.out.println("\nProcessing request completed.\n");
    }
    
    void HashCreate(lexicon6356 L, int m){
        /* This function create:
            T with m slots
            A with 15m slots
            m is user defined.
        */
        
        L.T = new int[m];
        L.A = new char[(15*m)];
        L.index_pointer_a = 1;
        
        // Initializing Load Factor.
        if(m != 0){
            L.load_factor = (float)L.key_in_t/(float)m;
        }
        
        //System.out.println("\nLexicon Initialized...." + m);
    }
    
    void HashEmpty(lexicon6356 L){
        // This function checks if Lexicon is empty.
        // Check if L is initializes.
        if(L.T == null || L.A == null){
            System.out.println("HASH TABLE NOT CREATED!!!!");
            return;
        }
        
        // Checking if T is empty or not.
        /*
        if(L.key_in_t == 0){
            System.out.println("\nT is Empty.\n");
        }
        else{
            System.out.println("\nT is Not Empty.\n");
        }
        
        // Checking if A is empty or not.
        if(L.index_pointer_a == 0 || L.index_pointer_a == 1){
            System.out.println("\nA is Empty.\n");
        }
        else if(L.key_in_t == 0){
            System.out.println("\nA contains garbage data.\n");
        }
        else{
            System.out.println("\nA is Not Empty.\n");
        }
        */
        float tempty = 100 - (L.load_factor * 100);

        System.out.println("\n\nT is " + tempty + "% empty.\n");
        
        float aempty = ((float)(L.A.length - L.index_pointer_a)/L.A.length)*100;
        System.out.println("A is " + aempty + "% empty.\n");
        //System.out.println("\nHashEmpty Coming soon....");
    }
    
    void HashFull(lexicon6356 L){
        // This function checks if Lexicon L can handle more words.
        // Check if L is initializes.
        if(L.T == null || L.A == null){
            System.out.println("HASH TABLE NOT CREATED!!!!");
            return;
        }
        
        // Checking for T.
        float tfull = L.load_factor * 100;

        System.out.println("\n\nT is " + tfull + "% full.\n");
        
        float afull = 100 - (((float)(L.A.length - L.index_pointer_a)/L.A.length)*100);
        System.out.println("A is " + afull + "% full.\n");
        //System.out.println("\nHashFull Coming soon....");
    }
    
    void HashPrint(lexicon6356 L){
        // This function prints Lexicon.
        //System.out.println("\nHashPrint Entered....");
        
        //Checking if T and A are created...
        if(L.T == null){
            System.out.println("HASH TABLE NOT CREATED!!!!");
            return;
        }
        
        // Printing Hash Table (T).
        int len = L.T.length;
        System.out.println("\nPrinting Hash Table T: ");
        for(int i = 0; i < len; i++){
            if(L.T[i] == 0){
                System.out.println(i + ": ");
            }
            else{
                System.out.println(i + ": " + L.T[i]);
            }
            
        }
        
        if(L.A == null){
            System.out.println("DATA ARRAY NOT CREATED OR DELETED!!!!");
            return;
        }
        
        // Printing Data Array (A).
        len = L.A.length;
        System.out.print("\nIMPORTANT NOTE: I have started array A[] from index '1' instead of '0' becaus in java all int array (T) elements are initialized to '0' by default.\n");
        System.out.print("\nA: ");
        for(int i = 1; i < len; i++){
            if(L.A[i] == '\0'){
                System.out.print('\\');
                continue;
            }
            System.out.print(L.A[i]);
        }
        
        //System.out.println("\nHashPrint Exited....");
    }
    
    void ReHashing(lexicon6356 L, int multiplier){
        // This function increases size of Hash Table T and performs rehashing for all entry in hash table.

        // Storing current state of T.
        int ttemp[] = L.T.clone();
        int Tm = (L.T.length*multiplier)+1; // +1 to mane number odd, increases chance of prime number.
        L.T = new int[Tm];
            
        // Re-Hashing all entries to new table from old table.
        for(int i = 0; i < ttemp.length; i++){
            if(ttemp[i] != 0){
                // Value present. Perform Re-Hashing.
                int j = ttemp[i], resum = 0;
                while(L.A[j] != '\0'){
                    resum = resum + L.A[j];
                    j++;
                        
                }
                if(resum == 0){
                    System.out.println("\n\nImproper indexing to A !!!!!\n\n");
                }
                int rehdashk = resum % L.T.length;
                int rehki, tlen = L.T.length;
                boolean COLLISION = true;
                for(int k = 0; k < tlen; k++){
                    // Probing till end or empty space in T.
                    rehki = (rehdashk + (k*k)) % tlen;

                    // Checking if collision.
                    if(L.T[rehki] == 0){
                        // Putting pointer to data in hash table.
                        L.T[rehki] = ttemp[i];
                        COLLISION = false;
                        break;
                    }
                }
                
                // Checking if collision.
                if(COLLISION){
                    L.T = null;
                    L.T = ttemp.clone();
                    ReHashing(L,(multiplier*2));
                    break;
                }
            }
        }
        
    }
    
    void HashInsert(lexicon6356 L, String word){
        // This function insert word into Lexicon.
        // Check if L is initializes.
        if(L.T == null || L.A == null){
            System.out.println("HASH TABLE NOT CREATED!!!!");
            return;
        }
        // Checking load factor.
        if(L.load_factor > 0.75){
            // Re-Hashing all entries to new table from old table.
            ReHashing(L, 2);
            // Update all the relevant variables.
            L.load_factor = (float)L.key_in_t/(float)L.T.length;
        }
        
        // Insertion process start.....
        // Calculate sum of ASCII value of characters in word.
        int wordlen = word.length(),sum = 0;
        for(int i = 0; i < wordlen; i++){
            sum = sum + word.charAt(i);
        }
        
        //System.out.println("h`(k): " + hkdash + "\tsum: " + sum + "\tT.length: " + L.T.length);
        
        // Finding location in hash table T for word.
        int hki=0;
        boolean COLLISION = true;
        
        while(COLLISION){
            int tlen = L.T.length;
            int hdashk = sum % tlen;
            for(int i = 0; i < tlen; i++){
                // Probing till end or empty space in T.
                hki = (hdashk + (i*i)) % tlen;

                // Checking if collision.
                if(L.T[hki] == 0){
                    COLLISION = false;
                    break;
                }
            }

            if(COLLISION){
                // Increase T.
                ReHashing(L, 2);
                // Update all relevant variables.
                L.load_factor = (float)L.key_in_t/(float)L.T.length;
            }
        }
        
        
        // Check if A is capable to hold word.
        while(!((L.A.length - L.index_pointer_a) > wordlen)){
            // Increase size of A.
            int alen = L.A.length;
            char atemp[] = L.A.clone();
            L.A = new char[(alen*2)];
            alen = atemp.length;
            // Re-Storing all values.
            System.arraycopy(atemp, 0, L.A, 0, alen);
        }
        
        // Insert values in T and A.
        L.T[hki] = L.index_pointer_a;
        // increment key_in_t after successfull insertion.
        L.key_in_t++;
        for(int i = 0; i < wordlen; i++){
            L.A[L.index_pointer_a] = word.charAt(i);
            L.index_pointer_a++;
        }
        L.A[L.index_pointer_a] = '\0';
        L.index_pointer_a++;
        
        // Update Load Factor of L (L.load_factor).
        L.load_factor = (float)L.key_in_t/(float)L.T.length;
        //System.out.println("\n" + word + " Inserted Successfully");
    }
    
    void HashDelete(lexicon6356 L, String word){
        // This function deletes word from lexicon.
        // Check if L is initializes.
        if(L.T == null || L.A == null){
            System.out.println("HASH TABLE NOT CREATED!!!!");
            return;
        }
        
        int wordlen = word.length(),sum = 0;
        for(int i = 0; i < wordlen; i++){
            sum = sum + word.charAt(i);
        }
        
        int tlen = L.T.length;
        int hdashk = sum % tlen,hki=0;
        boolean found = false;
        
        for(int i = 0; i < tlen; i++){
            hki = (hdashk + (i*i)) % tlen;
            int ind = L.T[hki],j=0;
            
            while(L.A[ind+j] != '\0'){
                if(j >= wordlen){
                    break;
                }
                if(L.A[ind+j] != (word.charAt(j))){
                    break;
                }
                
                j++;
            }
            
            if(j == wordlen){
                found = true;
                break;
            }
        }
        
        // Deletion section.
        if(found){
            // Deleting word from Data Array A.
            int ind = L.T[hki];
            while(L.A[ind] != '\0'){
                L.A[ind] = '*';
                ind++;
            }
            
            // Deleting hash entry from T.
            L.T[hki] = 0;
            L.key_in_t--;
            // Update load factor of L (L.load_factor).
            L.load_factor = (float)L.key_in_t/(float)L.T.length;
            
            System.out.println("\n'" + word + "' Deleted. \n");
        }
        else{
            // Word Not Found.
            System.out.println("\n" + word + " Not Found.\n");
        }
        
        //System.out.println("\nHashDelete Coming soon...." + word);
    }
    
    void HashSearch(lexicon6356 L, String word){
        // This function search word from lexicon.
        // Check if L is initializes.
        if(L.T == null || L.A == null){
            System.out.println("HASH TABLE NOT CREATED!!!!");
            return;
        }
        
        int wordlen = word.length(),sum = 0;
        for(int i = 0; i < wordlen; i++){
            sum = sum + word.charAt(i);
        }
        
        int tlen = L.T.length;
        int hdashk = sum % tlen,hki=0;
        boolean found = false;
        
        for(int i = 0; i < tlen; i++){
            hki = (hdashk + (i*i)) % tlen;
            int ind = L.T[hki],j=0;
            
            while(L.A[ind+j] != '\0'){
                if(j >= wordlen){
                    break;
                }
                if(L.A[ind+j] != (word.charAt(j))){
                    break;
                }
                
                j++;
            }
            
            if(j == wordlen){
                found = true;
                break;
            }
        }
        
        if(found){
            System.out.println("\n'" + word + "' found at T[" + hki + "] and Starting at A[" + L.T[hki] + "].");
        }
        else{
            System.out.println("\n'" + word + "' Not found in L.");
        }
        //System.out.println("\nHashSearch Coming soon...." + word);
    }
    
    void HashBatch(lexicon6356 L, File f){
        // This function perform multiple operations as stated in file.
        //System.out.println("in-hb");
        FileReader data_fr = null;
        try{
            // Opening file to read commands.
            data_fr = new FileReader(f);
            
            // Reading file and processing commands.
            int crcnt = 0;
            String temp_storage = "", action = "";
            boolean data_mode = false; // True if data mode.
            boolean data_cycle = false; // True if currently data cycle was going on in loop.
            while(crcnt != -1){
                crcnt = data_fr.read();
                char temp = (char)crcnt;
                
                switch(temp){
                    case ' ':
                        //System.out.print(temp_storage + ' ');
                        //System.out.print("-s-");
                        //temp_storage = "";
                        //char_ready = true;
                        if(data_mode){
                            // Data Mode.
                            temp_storage = temp_storage + temp;
                        }
                        else{
                            // Commmand Mode.
                            //System.out.println("Command: "+temp_storage);
                            action = temp_storage;
                            temp_storage = "";
                            data_mode = true;
                        }
                        
                    break;
                        
                    case '\n':
                        //System.out.print("-n-");
                        //System.out.print(temp_d.hr.uvin.fzs.kumarstoraged.esaistorage);
                        //temp_storage = "";
                        //char_ready = true;
                        if(!data_mode){
                            // Command Mode.
                            //System.out.println("Command: "+temp_storage);
                            action = temp_storage;
                            data_cycle = false;
                            //temp_storage = "";
                            
                        }
                        else{
                            // Data Mode.
                            //System.out.println("Data: "+temp_storage);
                            //temp_storage = "";
                            data_cycle = true;
                            data_mode = false;
                        }
                        
                        //////////////////////
                        // Command Processing....
                        switch(action){
                            case "10":
                                // Executing Insert command.
                                if(!data_cycle){
                                    System.out.println("\n\nINCOMPLETE INSERT COMMAND: Data Not present.\n");
                                    break;
                                }
                                
                                // Function call.
                                HashInsert(L, temp_storage);
                                
                            break;

                            case "11":
                                // Executing Delete command.
                                if(!data_cycle){
                                    System.out.println("\n\nINCOMPLETE DELETE COMMAND: Data Not present.\n");
                                    break;
                                }
                                
                                // Function call.
                                HashDelete(L, temp_storage);
                                
                            break;

                            case "12":
                                // Executing Search command.
                                if(!data_cycle){
                                    System.out.println("\n\nINCOMPLETE DELETE COMMAND: Data Not present.\n");
                                    break;
                                }
                                
                                // Function call.
                                HashSearch(L, temp_storage);
                                
                            break;

                            case "13":
                                // Executing Print command.
                                //Function call.
                                HashPrint(L);
                                
                            break;

                            case "14":
                                // Executing Create command.
                                if(!data_cycle){
                                    System.out.println("\n\nINCOMPLETE CREATE COMMAND: Data Not present.\n");
                                    break;
                                }
                                try{
                                    int s = Integer.parseInt(temp_storage);
                                    // Function call.
                                    HashCreate(L, s);
                                }
                                catch(NumberFormatException number10){
                                    System.out.println("\n\nNOT_A_VALID_NUMBER: Inappropriate data provide as 'm' for creating");
                                }
                                catch(Exception err10){
                                    System.out.println("\n\nInappropriate data provide as 'm' for creating");
                                    System.out.println(Arrays.toString(err10.getStackTrace()));
                                }
                            break;  

                            case "15":
                                // Executing Comment command.
                                // Said to ignore so simply passing.
                                //System.out.print("comment ignored" + '\n');
                                break;

                            default:
                                System.out.println("\nInvalid Operation command: " + action);
                                break;
                        }
                        action = "";
                        // Command processing completed.
                        //////////////////////
                        
                        // Resetting temp holder.
                        temp_storage = "";
                            
                    break;
                        
                        
                    default:
                        //System.out.print(temp);
                        if(crcnt == -1){
                            if(data_mode){
                                // Data Mode.
                                data_cycle = true;
                            }
                            else{
                                // Command Mode.
                                data_cycle = false;
                                action = temp_storage;
                            }
                            
                            //////////////////////
                            // Command Processing....
                            switch(action){
                                case "10":
                                    // Executing Insert command.
                                    if(!data_cycle){
                                        System.out.println("\n\nINCOMPLETE INSERT COMMAND: Data Not present.\n");
                                        break;
                                    }

                                    // Function call.
                                    HashInsert(L, temp_storage);

                                break;

                                case "11":
                                    // Executing Delete command.
                                    if(!data_cycle){
                                        System.out.println("\n\nINCOMPLETE DELETE COMMAND: Data Not present.\n");
                                        break;
                                    }

                                    // Function call.
                                    HashDelete(L, temp_storage);

                                break;

                                case "12":
                                    // Executing Search command.
                                    if(!data_cycle){
                                        System.out.println("\n\nINCOMPLETE DELETE COMMAND: Data Not present.\n");
                                        break;
                                    }

                                    // Function call.
                                    HashSearch(L, temp_storage);

                                break;

                                case "13":
                                    // Executing Print command.
                                    //Function call.
                                    HashPrint(L);

                                break;

                                case "14":
                                    // Executing Create command.
                                    if(!data_cycle){
                                        System.out.println("\n\nINCOMPLETE CREATE COMMAND: Data Not present.\n");
                                        break;
                                    }
                                    try{
                                        int s = Integer.parseInt(temp_storage);
                                        // Function call.
                                        HashCreate(L, s);
                                    }
                                    catch(NumberFormatException number10){
                                        System.out.println("\n\nNOT_A_VALID_NUMBER: Inappropriate data provide as 'm' for creating");
                                    }
                                    catch(Exception err10){
                                        System.out.println("\n\nInappropriate data provide as 'm' for creating");
                                        System.out.println(Arrays.toString(err10.getStackTrace()));
                                    }
                                break;  

                                case "15":
                                    // Executing Comment command.
                                    // Said to ignore so simply passing.
                                    //System.out.print("comment ignored" + '\n');
                                    break;

                                default:
                                    System.out.println("\nInvalid Operation command: " + action);
                                    break;
                            }
                            action = "";
                            // Command processing completed.
                            //////////////////////
                            
                        }
                        else{
                            temp_storage = temp_storage + temp;
                        }
                    break;
                            
                }
               
            }
            //System.out.println(temp_storage);
            // Closing file.
            data_fr.close();
            
        }
        catch(Exception batcherr){
            System.out.println("In batcherr..");
            System.out.println(Arrays.toString(batcherr.getStackTrace()));
            
        }
        finally{
            //System.out.println("\nIn finally..");
            if(data_fr != null){
                try {
                    data_fr.close();
                } catch (IOException ex) {
                    Logger.getLogger(hashing6356.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        //System.out.println("out-hb");
    }
    
    public void finalize(){
        // To remove all file objects if some is still remaining.
        /* Template for closing file.
        if(file!=null)
            file.close();
        */

    }
}