/*
 * kartuves.java
 * 
 * class kartuves, class ManoLangas
 * 
 * kartuves: zaidimo klasė, gauna spejima patikina jį generuoja pranešimus
 * ManoLangas: klase skirta grafikai, sukuria langa nustato mygtukus, piesia ekrane ir t.t.
 * 
 * Rima Žurauskaitė ir Jovita 
 * 
 * el. paštas: 
 */

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.lang.*;


public class kartuves extends Frame implements ActionListener{	
	
	static final int MAX_KLAIDOS =7;									// koks maksimalus leistinas klaidu skaicius
	static final String DATA_FILE = "zodziai.txt";			// failas kuriame saugomi zodziai
	
	private Button mygtukasPradeti, mygtukasSpek;					// atributai
	private TextField tekstoLaukas;
	private char spejimas;
	private String spetosRaides = "";
	private String zinute = "";
	private String pranesimas = "";
	private int klaidos = 0;
	private Frame langas;
	private StringBuffer slaptasZodis;							// zodis matomas zaidejui
	private String zodis;														// zodis kuri reikia atspeti
	private List<Character> neatspetosRaides;				// kolekcija (sarasas) dar neatspetu raidziu
	
	class ManoLangas extends Frame{	
		
		public ManoLangas(String s) {			// metodas sukonstruojantis zaidimo langa
			super(s);
			setBackground(new Color(0xCCFF7F));  
			setSize(550,300);
			
			Label t;
			t = new Label("Spejama raide:");	// sukuria label
			t.setBackground(new Color(0x99FF00));	
			
			setLayout(new FlowLayout());
			
			setVisible(true);	
			
			mygtukasPradeti = new Button("Kartoti");
			mygtukasSpek = new Button("Spek");
			mygtukasPradeti.setBackground (new Color(0xCC99BF));	
			mygtukasSpek.setBackground (new Color(0x994C85	));	
			
			tekstoLaukas = new TextField(1);
			tekstoLaukas.setBackground (new Color( 0xE5FFBF ));
			tekstoLaukas.setText ("");
			
			add(mygtukasPradeti);
			add(t);
			add(tekstoLaukas);
			add(mygtukasSpek);
			
			addWindowListener(new WindowAdapter() {		// kad langa galima butu uzdaryti
				public void windowClosing(WindowEvent e) {
				dispose();
				System.exit(0);
			} });}
			
			public void paint(Graphics g) {				// metodas piesiantis visa vaizda lange
        // piesia
				int baseY = 250;
				if (klaidos >  0){    
								g.drawLine(90, baseY,200,baseY);
				}
				if (klaidos >  0){  
								g.drawLine(125,baseY,125,baseY-100);
				}
				if (klaidos >  1){
								g.drawLine(110,baseY,125,baseY-15);
				}
				if (klaidos >  1){
								g.drawLine(140,baseY,125,baseY-15);
				}
				if (klaidos >  2){   
								g.drawLine(125,baseY-100,175,baseY-100);
				}
				if (klaidos >  2){
								g.drawLine(125,baseY-85,140,baseY-100);
				}
				if (klaidos >  3){   
								g.drawLine(175,baseY-100,175,baseY-75);
				}
				if (klaidos >  3){   
								g.drawOval(170,baseY-75,10,12);
				}
				if (klaidos >  4){
								g.drawOval(170,baseY-65,15,25);
				}
				if (klaidos >  5){ 
								g.drawLine(160,baseY-65,170,baseY-60);
				}
				if (klaidos > 5){
								g.drawLine(183,baseY-60,193,baseY-65);
				}
				if (klaidos > 6){   
								g.drawLine(165,baseY-30,170,baseY-45);
				}
				if (klaidos > 6){
								g.drawLine(183,baseY-45,193,baseY-30);
				}			
				// rodo pranesimus ekrane
				String kasJauSpeta = "Jau spetos raides: " + spetosRaides;
				g.drawString( zinute, 100, baseY + 25 );
				g.drawString( kasJauSpeta, 25, baseY + 45 );
				g.drawString( pranesimas, 250, baseY - 80 );
				g.drawString( new String(slaptasZodis), 180, baseY - 150);
      }
	}

	public void actionPerformed(ActionEvent ivyk) {			// ka daro mygtukai
			if (ivyk.getSource() == mygtukasSpek) {
				gaukSpejima();
			}
			if (ivyk.getSource() == mygtukasPradeti) {
				langas.setVisible(false);
				new kartuves();
			}
	}
	
	public void gaukSpejima () {		// gauna spejimą iš teksto lauko kurį įveda žaidėjas
		
		String tekstas;		// kintamieji
		tekstas = tekstoLaukas.getText();		// gaunu spejima
		
		tekstas = tekstas.toUpperCase();		//  spejima padariau is didziuju raidziu, kad butu nesvarbu ar vartotojas įvedė didžiaja ar mažaja
		
		if (tekstas.length()  <= 0 ) return;
		spejimas = tekstas.charAt(0);					// jei įvedė daugiau nei viena raide pasimame pačią pirmają
		
		if (!Character.isLetter(spejimas)) {		// jei koki samarleka iveda o ne raide
			zinute = "Žodžiai sudaryti tik iš raidžių";
			langas.repaint();
			return;
		}
			
		if (spetosRaides.indexOf(spejimas) != -1) {			// speta raide kartojasi
			zinute = "Tokia raide jau speta";
		}
		else if (neatspetosRaides.indexOf(spejimas) == -1) {				// raides neatspejo
			
			klaidos++;
			if ( klaidos >= MAX_KLAIDOS) {								// jei pralaimejo
				zinute = "Pralaimejote!";
				pranesimas = "Noredami zaisti dar karta spauskite Kartoti.";
				langas.repaint();
				return;
			}	
			zinute = "Raides zodyje nera";
			spetosRaides = spetosRaides + spejimas + " ";	
		}
		else {																				// raide atspejo
			zinute = " ";
			spetosRaides = spetosRaides + spejimas + " ";
			int indeksas = neatspetosRaides.indexOf(spejimas);
			while ( indeksas != -1 ) {									// perziuriu kol patikrinu visas pasikartojancias raides
				neatspetosRaides.remove(indeksas);
				indeksas = neatspetosRaides.indexOf(spejimas);
			}	
			for (int i = 0; i < zodis.length(); i++) {	// pagaminu zodi is **** yrasau atspetas raides
				if (zodis.charAt(i) == spejimas)
					slaptasZodis.setCharAt(i, spejimas);
			}
		}		
		
		if (neatspetosRaides.isEmpty()) {							// jei pralose
			zinute = "Sveikiname! Jus laimejote!";
			pranesimas = "Noredami zaisti dar karta pauskite Kartoti.";
			langas.repaint();
			return;
		}
		
		langas.repaint();			
		tekstoLaukas.setText("");		
	}


	public kartuves () {
		langas = new ManoLangas("KARTUVĖS");	// sukuriu langa
		
		mygtukasPradeti.addActionListener(this);		// aktyvina mygtukus
		mygtukasSpek.addActionListener(this);
		
		GetWord file = new GetWord();							// gaunu atsitiktini zodi
		zodis = file.gaukZodi("zodziai.txt");
		
		
		neatspetosRaides = new ArrayList<Character>();			/* kolekcijas naudoju */
		
		String tmp = "";							// kuriu neatspetu raidziu sarasa ir zodi sudaryta is **** "
		for (int i = 0; i < zodis.length(); i++){
			neatspetosRaides.add(zodis.charAt(i));
			tmp += "*";
		}
		slaptasZodis = new StringBuffer(tmp);
	}
}
	
