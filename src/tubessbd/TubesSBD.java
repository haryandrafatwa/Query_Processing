/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tubessbd;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;
import java.lang.Math; 
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 *
 * @author User
 */
public class TubesSBD {

    /**
     * @param args the command line arguments
     */
    public static void menu1(String[] data)
    {
        int  k = 0;
            for(int i=2;i<9;i+=3)
            {
                int hasil=Integer.parseInt(data[1])/Integer.parseInt(data[i]);
                int fan = Integer.parseInt(data[1])/(Integer.parseInt(data[0])+Integer.parseInt(data[i+2]));
                int j = 11;
                System.out.println("Bfr "+data[j+k]+     " = "+hasil);
                System.out.println("Fan Out Ratio "+data[j+k]+" = "+fan);
                System.out.println("");
                k = k + 4;
            }
    }
    
    public static void menu2(String[] data) 
    {
        int  k = 0;
        int l = 3;
        for(int i=2;i<9;i+=3)
        {
            double jmlblok= Double.parseDouble(data[l])/(Double.parseDouble(data[1])/Double.parseDouble(data[i]));
            double indeksblok = Double.parseDouble(data[l])/(Double.parseDouble(data[1])/(Double.parseDouble(data[0])+Double.parseDouble(data[i+2])));
            int j = 11;
            System.out.println("Tabel Data "+data[j+k]+     " = "+Math.ceil(jmlblok)+ " blok");
            System.out.println("Indeks "+data[j+k]+" = "+Math.ceil(indeksblok)+" blok");
            System.out.println("");
            k = k + 4;
            l = l + 3;
        }
    }
    
    public static void menu3(String[] data, int input, String tabel) {
        int posTable=0;
        int tempTable = 0;
//        for(posTable=0;!tabel.equalsIgnoreCase(data[posTable]) && (posTable<data.length-1);posTable++)//ngecek apakah table ada didatabase atau engga
//        {
//        }
        for (posTable=0;(posTable<data.length-1);posTable++) {
            if(tabel.equalsIgnoreCase(data[posTable])){
                tempTable = posTable;
                if(tabel.equalsIgnoreCase(data[11])){posTable = 2;}
                if(tabel.equalsIgnoreCase(data[15])){posTable = 5;}
                if(tabel.equalsIgnoreCase(data[19])){posTable = 8;}
                double hasil=Math.ceil(input/(Double.parseDouble(data[1])/Double.parseDouble(data[posTable])));//cari bfr duls
                double hasil2 = (double) Math.ceil(input/(Double.parseDouble(data[1])/(Double.parseDouble(data[0])+Double.parseDouble(data[posTable+2]))))+1;//  cari fan
                System.out.println("Menggunakan indeks, jumlah blok yang diakses : "+hasil2);
                System.out.println("Tanpa indeks, jumlah blok yang diakses : "+hasil);
                posTable = tempTable;
            }
        }
    }
    
    //A1 KEY
    public static int menu4a1K(String[] data, int postable)
    {
        int i = postable-8;
        int a1 = Integer.parseInt(data[i])/2;
        
        return a1;
    }
    
    //AI NON KEY
    public static int menu4a1(String[] data, int postable)
    {
        int i = postable-8;
        int a1 = Integer.parseInt(data[i]);
       
        return a1;
    }
    
    //A2 
    public static double menu4a2(String[] data, int postable) {
      //cari fan ratio duls
      int fan = Integer.parseInt(data[1])/(Integer.parseInt(data[0])+Integer.parseInt(data[postable-7]));
        
      double b= Double.parseDouble(data[postable-8]);
        
      double h = Math.ceil(Math.log(b))/Math.log(fan);
        
      return Math.ceil(h)+1;
    }
    
    public static int menu4join(String[] data, int postable1, int postable2) {
        int a2 = (Integer.parseInt(data[postable2-8])*Integer.parseInt(data[postable1-8]))+Integer.parseInt(data[postable1-8]);
        return a2;
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here
        
        String data = null;
        int i = 0;
        BufferedReader br;//sebuah object untuk membaca sebuah file 
        
        try {
            //proses membaca file txt dan memasukannya kedalam sebuah string
            br = new BufferedReader(new FileReader("D:\\Kuliah\\Document\\Semester 4\\Sistem Basis Data\\Code\\TubesSBD\\data.txt"));
            data=br.readLine();
        }
        
        catch (Exception e) {
            //untuk menghandle error pada saat pembacaan data
            System.out.println("File Data tidak ditemukan!");
        }
        
        String datatext[] = data.split(";"); //datatext
        
        System.out.println("------------------------------------");
        System.out.println("|   TUGAS BESAR SISTEM BASIS DATA  |");
        System.out.println("------------------------------------");
        System.out.println("| Dikerjakan oleh:                 |");
        System.out.println("| 1. Aldi Pranadia                 |");
        System.out.println("| 2. Haryandra Fatwa A.            |");
        System.out.println("| 3. Talitha Kayla A.              |");
        System.out.println("| 4. Vina Mutiara                  |");
        System.out.println("|                                  |");
        System.out.println("| Kelas:IF-41-04                   |");
        System.out.println("------------------------------------");
        System.out.println("");
        System.out.println("Menu Utama: ");
        System.out.println("1. Bfr dan Fanout Ratio");
        System.out.println("2. Total blok data dan blok index");
        System.out.println("3. Tampilkan Jumlah Blok yang Diakses untuk pencarian Rekord");
        System.out.println("4. Tampilkan QEP dan Cost");
        System.out.println("5. Tampilkan isi File Shared Pool");
        Scanner pilihan = new Scanner(System.in);    //scanner untuk inputan
        System.out.print("Pilihan menu : ");
        String inputan = pilihan.nextLine();
        System.out.println("");
        
        if(inputan.equals("1")){
            menu1(datatext);
        }else if (inputan.equals("2")) {
            menu2(datatext);
        }else if (inputan.equals("3")) {
            Scanner inputrecord = new Scanner(System.in);
            System.out.print("Cari Rekord ke- : ");
            int record = inputrecord.nextInt();
            System.out.print("Nama Tabel : ");
            String tabel = inputrecord.next();
            System.out.println("");
            menu3(datatext,record,tabel);
        }else if (inputan.equals("5")) {
            try (Stream<String> stream = Files.lines(Paths.get("SharedPool.txt"))) {
                stream.forEach(System.out::println);
            }
            catch (Exception ggl){
                System.out.println("File Shared Pool tidak ditemukan!");
            } 
        }         
        
        if (inputan.equals("4")) {
            Scanner inputQuery = new Scanner(System.in);
            System.out.print("Masukan Query : ");
            String Query = inputQuery.nextLine();
            
            //proses untuk mempermudah dalam melakukan split data dan mendeteksi sebuah error
            String a  = Query.replace(";;"," & ");
            String b  = Query.replace(";"," ;");
            String c  = b.replace(",", " ");
            String d  = c.replace("("," ");
            String e  = d.replace(")"," ");
            String f  = e.replace("="," ");
            String g  = f.replace("   ", " ");
            String h  = g.replace("  ", " ");
            String m  = h.replace("'", "");        
        
            String query[] = m.split(" "); //inputan

            if (query[0].equalsIgnoreCase("select"))//pengecekan apakah terdapat select
            {
                if (query[query.length-1].equalsIgnoreCase(";")) //pengecekan apakah terdapat sebuah ;
                {
                    String cekSelect = "";
                    for (int j = 0;!cekSelect.equalsIgnoreCase("&") && j<query.length;j++)
                    {
                        cekSelect = query[j];
                    }
                    if (!cekSelect.equals("&"))
                    {
                        int posisiFrom = 0;
                        for (posisiFrom=0;!"from".equalsIgnoreCase(query[posisiFrom]) && posisiFrom<query.length-1;posisiFrom++)
                        {
                        }
                        if("from".equalsIgnoreCase(query[posisiFrom]))
                        {
                            int posisiJoin = 0;
                            for(posisiJoin = 0;!"join".equalsIgnoreCase(query[posisiJoin]) && posisiJoin<query.length-1;posisiJoin++)//mencari posisi join
                            {
                            }
                            if("join".equalsIgnoreCase(query[posisiJoin]))
                            {

                                int posisiOn = 0;
                                for(posisiOn = 0;!"on".equalsIgnoreCase(query[posisiOn]) && posisiOn<query.length-1;posisiOn++)
                                {
                                }
                                if("on".equalsIgnoreCase(query[posisiOn]))
                                {
                                    String varT1 = "";
                                    String tabel1 = "";
                                    if (posisiJoin > 1)
                                    {
                                        tabel1 = query[posisiJoin-2];
                                        varT1 = query[posisiJoin-1];
                                    }
                                    else
                                    {
                                        System.out.println("SQL Error (Table Name is not found)");
                                    }
                                    String varT2 = "";
                                    String tabel2 ="";
                                    if(posisiOn > 1)
                                    {
                                        tabel2 = query[posisiOn - 2];
                                        varT2 = query[posisiOn - 1];
                                    }
                                    else
                                    {
                                        System.out.println("SQL Error (Table Name is not found)");
                                    }

                                    int posTable1DB =0;
                                    int posTable2DB =0;
                                    if(!tabel1.equalsIgnoreCase("") && !tabel2.equalsIgnoreCase(""))//cekapakah tabel ditemukan di query
                                    {        
                                        while(!tabel1.equalsIgnoreCase(datatext[posTable1DB]) && (posTable1DB<datatext.length-1)){
                                            posTable1DB++;
                                        }
                                        while(!tabel2.equalsIgnoreCase(datatext[posTable2DB]) && (posTable2DB<datatext.length-1)){
                                            posTable2DB++;
                                        }
//                                        for(posTable1DB=0;!tabel1.equalsIgnoreCase(datatext[posTable1DB]) && (posTable1DB<datatext.length-1);posTable1DB++)
//                                        {
//                                        }
//                                        for(posTable2DB=0;!tabel2.equalsIgnoreCase(datatext[posTable2DB]) && (posTable2DB<datatext.length-1);posTable2DB++)
//                                        {
//                                        }
                                        if(tabel1.equalsIgnoreCase(datatext[posTable1DB]) && tabel2.equalsIgnoreCase(datatext[posTable2DB]))//jika table join sudah ditemukan didalam databae
                                        {  
                                            String pkey1="";
                                            String pkey2="";
                                            int dataafter1=1;
                                            int dataafter2=1;
                                            while(!query[posisiOn+1].equalsIgnoreCase((varT1+"."+datatext[posTable1DB+dataafter1])))//untuk mengecek m. + nama primary key apakah ada didalam database
                                            {
                                                dataafter1++;
                                            }
                                            while(!query[posisiOn+2].equalsIgnoreCase((varT2+"."+datatext[posTable2DB+dataafter2]))&& (dataafter2<3))
                                            {
                                                dataafter2++;
                                            }
                                            if(query[posisiOn+1].equalsIgnoreCase((varT1+"."+datatext[posTable1DB+dataafter1])))
                                            {                                                  
                                                pkey1=datatext[posTable1DB+1];
                                                if(query[posisiOn+2].equalsIgnoreCase((varT2+"."+datatext[posTable2DB+dataafter2])))
                                                {   
                                                    pkey2=datatext[posTable1DB+1];
                                                }
                                                else
                                                {
                                                    System.out.println("SQL Error (Primary Key isn't right)");
                                                }
                                            }       
                                            else
                                            {
                                                System.out.println("SQL Error (Primary Key isn't right)");
                                                System.out.println(varT1+", "+varT2+", "+posTable1DB+", "+posTable2DB+query[posisiOn+1]);
                                            }
                                            if(!pkey1.equalsIgnoreCase("") && !pkey2.equalsIgnoreCase(""))
                                            {
                                                int fold = -1;
                                                int cek1 = 1;
                                                String kolomTable1 =(pkey1+", ");
                                                String kolomTable2 =(pkey2+", ");
                                                while(cek1<posisiFrom)
                                                {                                
                                                    int k = 0;
                                                    boolean finish = false;
                                                    while(k<3 && finish == false)
                                                    {
                                                        if(query[cek1].equalsIgnoreCase(datatext[posTable1DB+k]))
                                                        { 
                                                            kolomTable1 = (kolomTable1+query[cek1]+", ");
                                                            finish = true;
                                                        }
                                                        else
                                                        {
                                                            if(query[cek1].equalsIgnoreCase(datatext[posTable2DB+k]))
                                                            {
                                                                kolomTable2 = (kolomTable2+query[cek1]+", ");
                                                                finish = true;
                                                            }
                                                            else
                                                            {
                                                                finish=false;
                                                            }
                                                        }
                                                        k++;
                                                    }
                                                    if(finish==false)
                                                    {
                                                        fold++;
                                                    }
                                                    cek1++;
                                                }
                                                if(fold<1)
                                                {                    
                                                    //QEP1
                                                    System.out.println("Nama Tabel (1) : "+tabel1);
                                                    System.out.println("List Kolom : "+kolomTable1);
                                                    System.out.println("Nama Tabel (2) : "+tabel2);
                                                    System.out.println("List Kolom : "+kolomTable2);

                                                    System.out.println("PROJECTION "+kolomTable1+" -- on the fly");
                                                    System.out.println("JOIN "+varT1+"."+pkey1+" = "+varT2+"."+pkey2);
                                                    System.out.println(tabel1+" "+tabel2);
                                                    int cost = menu4join(datatext,posTable1DB,posTable2DB);
                                                    System.out.println("Cost (worst case) : "+cost);
                                                    System.out.println(" ");
                                                    
                                                    //QEP2
                                                    System.out.println("Nama Tabel (1) : "+tabel1);
                                                    System.out.println("List Kolom : "+kolomTable1);
                                                    System.out.println("Nama Tabel (2) : "+tabel2);
                                                    System.out.println("List Kolom : "+kolomTable2);

                                                    System.out.println("PROJECTION "+kolomTable1+" -- on the fly");
                                                    System.out.println("JOIN "+varT1+"."+pkey1+" = "+varT2+"."+pkey2);
                                                    System.out.println(tabel2+" "+tabel1);
                                                    int cost2 = menu4join(datatext,posTable2DB,posTable1DB);
                                                    System.out.println("Cost (worst case) : "+cost2);

                                                    if(cost<cost2) {
                                                        String max = "QEP#1";
                                                        System.out.println("QEP OPTIMAL : "+max);
                                                    }else {
                                                        String max = "QEP#2";
                                                        System.out.println("QEP OPTIMAL : "+max);
                                                    }

                                                    try {
                                                        FileWriter writer2 = new FileWriter("SharedPool.txt", true);
                                                        BufferedWriter bufferedWriter = new BufferedWriter(writer2);
                                                        bufferedWriter.newLine();
                                                        bufferedWriter.write("Query : "+Query);
                                                        bufferedWriter.newLine();
                                                        bufferedWriter.write("PROJECTION "+kolomTable1+" -- on the fly");
                                                        bufferedWriter.newLine();
                                                        bufferedWriter.write("JOIN "+varT1+"."+pkey1+" = "+varT2+"."+pkey2);
                                                        bufferedWriter.newLine();
                                                        bufferedWriter.write(tabel1+" "+tabel2);
                                                        bufferedWriter.newLine();
                                                        bufferedWriter.write("Cost (worst case) : "+cost);
                                                        bufferedWriter.newLine();
                                                        bufferedWriter.write(" ");
                                                        bufferedWriter.newLine();
                                                        bufferedWriter.newLine();
                                                        bufferedWriter.write("Query : "+Query);
                                                        bufferedWriter.newLine();
                                                        bufferedWriter.write("PROJECTION "+kolomTable1+" -- on the fly");
                                                        bufferedWriter.newLine();
                                                        bufferedWriter.write("JOIN "+varT1+"."+pkey1+" = "+varT2+"."+pkey2);
                                                        bufferedWriter.newLine();
                                                        bufferedWriter.write(tabel2+" "+tabel1);
                                                        bufferedWriter.newLine();
                                                        bufferedWriter.write("Cost (worst case) : "+cost2);
                                                        bufferedWriter.newLine();
                                                        if(cost<cost2) {
                                                            String max = "QEP#1";
                                                            bufferedWriter.write("QEP OPTIMAL : "+max);
                                                        }else {
                                                            String max = "QEP#2";
                                                            bufferedWriter.write("QEP OPTIMAL : "+max);
                                                        }
                                                        bufferedWriter.close();    
                                                    }catch (Exception gagal2) {

                                                    }

                                                }
                                                else
                                                {
                                                    System.out.println("SQL Error (Data not found)");
                                                }
                                            }                                                 
                                        }
                                        else
                                        {
                                            System.out.println("SQL Error(table not found)");
                                        }
                                    }
                                    else
                                    {
                                        System.out.println("SQL Error (Syntax Error)");
                                    }
                                }
                                else
                                {            
                                    System.out.println("SQL error (missing ON)");
                                }

                            }
                            else //jika sebuah query tidak terdapat join atau sebuah query select satu tabel
                                { 
                                   String tabel=query[posisiFrom+1];
                                   int posTable=0;
                                   for(posTable=0;!tabel.equalsIgnoreCase(datatext[posTable]) && (posTable<datatext.length-1);posTable++)//ngecek apakah table ada didatabase atau engga
                                   {}
                                if(tabel.equalsIgnoreCase(datatext[posTable]))
                                {
                                    int cek = 1;
                                    int fold = 0;
                                    String Listkolom1 = "";
                                    for(cek=1;cek<posisiFrom;cek++)
                                    {   
                                        int l = 0;
                                        boolean finish = false;
                                        for(l=0;l<3 && finish == false;l++)
                                        {
                                            if(query[cek].equalsIgnoreCase(datatext[posTable+l]))//jika isi query cek ada didalam database
                                            {
                                                Listkolom1=(Listkolom1+query[cek]+", ");
                                                finish=true;
                                            }
                                        }
                                        if(finish == false)
                                        {
                                            fold++;
                                        }
                                    }

                                    if(fold<1)
                                    {                  
                                        int posisiwhere = 0;
                                        for (posisiwhere=0;!"where".equalsIgnoreCase(query[posisiwhere]) && posisiwhere<query.length-1;posisiwhere++) {

                                        }
                                        if("where".equalsIgnoreCase(query[posisiwhere])) {
                                            String kolomwhere = query[posisiwhere+1];
                                            int poskolomwhere = 0;
                                            for(poskolomwhere=posTable;!kolomwhere.equalsIgnoreCase(datatext[poskolomwhere]) && (poskolomwhere<posTable+3);poskolomwhere++)//ngecek apakah table ada didatabase atau engga
                                            {
                                            }
                                            if (kolomwhere.equalsIgnoreCase(datatext[poskolomwhere])) {

                                                String kondisi = query[posisiwhere+2];

                                                System.out.println("Nama Tabel : "+tabel);
                                                System.out.println("List Kolom : "+Listkolom1);
                                                //System.out.println("Where : "+kondisi);
                                                System.out.println("QEP #1");
                                                System.out.println("PROJECTION "+Listkolom1+" -- on the fly");

                                                if (poskolomwhere == posTable+1) {


                                                    System.out.println("SELECTION "+kolomwhere+" A1 Key");
                                                    System.out.println(tabel);
                                                    int cost1 = menu4a1K(datatext,posTable);
                                                    System.out.println("Cost : "+cost1);
                                                    System.out.println(" ");
                                                    System.out.println("QEP #2");
                                                    System.out.println("PROJECTION "+Listkolom1+" -- on the fly");
                                                    System.out.println("SELECTION "+kolomwhere+" A2");
                                                    System.out.println(tabel);
                                                    double cost2 = menu4a2(datatext,posTable);
                                                    System.out.println("Cost : "+cost2);

                                                    if (cost1<cost2) {
                                                        System.out.println("QEP OPTIMAL : QEP#1");
                                                    }else {
                                                        System.out.println("QEP OPTIMAL : QEP#2");
                                                    }

                                                }
                                                else if (poskolomwhere != posTable+1) {
                                                    System.out.println("SELECTION "+kolomwhere+" A1");
                                                    System.out.println(tabel);
                                                    int cost1 = menu4a1(datatext,posTable);
                                                    System.out.println("Cost : "+cost1);
                                                    System.out.println(" ");
                                                    System.out.println("QEP #2");
                                                    System.out.println("PROJECTION "+Listkolom1+" -- on the fly");
                                                    System.out.println("SELECTION "+kolomwhere+" A2");
                                                    System.out.println(tabel);
                                                    double cost2 = menu4a2(datatext,posTable);
                                                    System.out.println("Cost : "+cost2);

                                                    if (cost1<cost2) {
                                                        System.out.println("QEP OPTIMAL : QEP#1");
                                                    }else {
                                                        System.out.println("QEP OPTIMAL : QEP#2");
                                                    }
                                                    try {
                                                    FileWriter writer2 = new FileWriter("SharedPool.txt", true);
                                                    BufferedWriter bufferedWriter = new BufferedWriter(writer2);
                                                    bufferedWriter.newLine();
                                                    bufferedWriter.write("Query : "+Query);
                                                    bufferedWriter.newLine();
                                                    bufferedWriter.write("PROJECTION "+Listkolom1+" -- on the fly");
                                                    bufferedWriter.newLine();
                                                    bufferedWriter.write("SELECTION "+kolomwhere+" A1");
                                                    bufferedWriter.newLine();
                                                    bufferedWriter.write("Cost : "+cost1);
                                                    bufferedWriter.newLine();
                                                    bufferedWriter.newLine();
                                                    bufferedWriter.write("Query : "+Query);
                                                    bufferedWriter.newLine();
                                                    bufferedWriter.write("PROJECTION "+Listkolom1+" -- on the fly");
                                                    bufferedWriter.newLine();   
                                                    bufferedWriter.write("SELECTION "+kolomwhere+" A2");
                                                    bufferedWriter.newLine(); 
                                                    bufferedWriter.write("Cost : "+cost2);
                                                    bufferedWriter.newLine();
                                                    if (cost1<cost2) {
                                                        bufferedWriter.write("QEP OPTIMAL : QEP#1");
                                                    }else {
                                                        bufferedWriter.write("QEP OPTIMAL : QEP#2");
                                                    }
                                                    bufferedWriter.close();    
                                                    }catch (Exception gagal2) {

                                                    }
                                                }
                                            }
                                            else {
                                                System.out.println("Syntax Error;");
                                            }
                                        }
                                        else {
                                            System.out.println("Nama Tabel : "+tabel);
                                            System.out.println("List Kolom : "+Listkolom1);
                                        }
                                    }
                                    else
                                    {
                                        System.out.println("SQL Error (Data not found)");
                                    }
                                }
                                else
                                {
                                    System.out.println("SQL Error (Table not found)");
                                }   
                            }                        
                        }
                        else
                        {
                            System.out.println("SQL Error (Missing 'From')");
                        }                   
                    }
                    else
                    {
                        System.out.println("SQL Error (Syntax Error ';')");
                    }                
                }
                else if(query[query.length-1].equalsIgnoreCase("&"))
                {
                    System.out.println("SQL Error (Syntax Error ';')");
                }
                else
                {
                    System.out.println("SQL Error (Missing ;)");
                } 
            }
            else
            {
                System.out.println("SQL Error(Syntax Error 'select')");
            }
        }
    }
}