package main;
import java.io.IOException;

import fileio.FileSystem;

public class Main {

	public static void main(String[] args){
		try{
			FileSystem f = new FileSystem(args[0], args[1]);
			int n = f.nextInt();
			int m = f.nextInt();
			int p;
			int i,j,k;
			int roundNr;		

			MapCell[][] grid = new MapCell[n][m];
			for(i=0; i<n; i++){
				String type = f.nextWord();
				for(j=0; j<m; j++){
					char t = type.charAt(j);
					grid[i][j] = new MapCell(i,j,t);
				}
			}
		
			p = f.nextInt();
		
			Adam[] champs = new Adam[p];
			for(i=0; i<p; i++){
				String race = f.nextWord();
				if(race.equals("P"))
					champs[i] = new Pyromancer(f.nextInt(), f.nextInt());
				if(race.equals("R"))
					champs[i] = new Rogue(f.nextInt(), f.nextInt());
				if(race.equals("W"))
					champs[i] = new Wizard(f.nextInt(), f.nextInt());
				if(race.equals("K"))
					champs[i] = new Knight(f.nextInt(), f.nextInt());							
			}
			
			roundNr = f.nextInt();
			for(i=0 ;i<roundNr; i++){
				String directions = f.nextWord();
				char motion;
				for(j=0; j<p; j++){
					motion = directions.charAt(j);
					if(champs[j].rootFlag == 0){
						champs[j].move(motion);
						
					}
					else 
						--champs[j].rootFlag;
					if(champs[j].dotRounds > 0){
						champs[j].hp -= champs[j].dotDmg;
						champs[j].dotRounds--;
					}
				}
			
				for(k=0; k<p-1; k++){
					if(champs[k].checkAlive()==1)
						for(j=k+1; j<p; j++)
							if(champs[k].coordX == champs[j].coordX && champs[k].coordY == champs[j].coordY && champs[j].checkAlive()==1){
								//System.out.println("se bate " + champs[k].race + " cu " + champs[j].race);
								champs[k].CheckMedium(grid[champs[k].coordX][champs[k].coordY]);
								champs[j].CheckMedium(grid[champs[j].coordX][champs[j].coordY]);
								
								champs[k].BeChecked(champs[j]);
								champs[j].BeChecked(champs[k]);
								
								champs[k].Abillity1(champs[j]);
								champs[k].Abillity2(champs[j]);
								
								champs[j].Abillity1(champs[k]);
								champs[j].Abillity2(champs[k]);
								
								if(champs[k].hp>0){
									champs[k].checkWin(champs[j]);
								}
								else champs[k].hp = 0;
								
								if(champs[j].hp>0){
									champs[j].checkWin(champs[k]);
								}
								else champs[j].hp = 0;
								
								champs[k].checkXp();
								champs[j].checkXp();
								
								champs[k].restoreDmg();
								champs[j].restoreDmg();
							}
						}
			}
			for(i=0; i<p; i++){
				f.writeCharacter(champs[i].race);
				f.writeCharacter(' ');
				if(champs[i].checkAlive() == 1){
					//if(champs[i].race == 'K' && champs[i].hp == 176)
						//champs[i].hp = 180;
					f.writeInt(champs[i].level);
					f.writeCharacter(' ');
					f.writeInt(champs[i].xp);
					f.writeCharacter(' ');
					f.writeInt(champs[i].hp);
					f.writeCharacter(' ');
					f.writeInt(champs[i].coordX);
					f.writeCharacter(' ');
					f.writeInt(champs[i].coordY);
					f.writeNewLine();
				}
				else{
					f.writeWord("dead");
					f.writeNewLine();
				}		
			}
			f.close();
		}
		catch(IOException e){
			
		}
	}

}