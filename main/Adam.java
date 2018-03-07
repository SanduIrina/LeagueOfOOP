package main;

public class Adam {
	int coordX, coordY;
	int xp,level,hp,hpUp,originalHP;
	float fSkill, fSkillUp, sSkill, sSkillUp, fSkillBase, sSkillBase;
	char race;
	int dotRounds;
	float dotDmg;
	int rootFlag;
	float fWiz,sWiz;
	
	public Adam(int CoordX, int CoordY, char race){
		this.race = race;
		this.coordX = CoordX;
		this.coordY = CoordY;
	}
		
	public void checkWin(Adam victim){
		if(victim.hp <= 0){
			xp += Math.max(0, 200 - (level - victim.level) * 40);
		}
	}
	
	public void checkXp(){};
	
	public void move(char direction){
		if(direction == 'U')
			coordX--;
		if(direction == 'D')
			coordX++;
		if(direction == 'L')
			coordY--;
		if(direction == 'R')
			coordY++;
		if(direction == '_')
			return;
	}
	
	public int checkAlive(){
		if(hp <= 0){
			hp = 0;
			return 0;
		}
		else return 1;
	}
	
	public void CheckMedium(MapCell cell){};
	
	public void BeChecked(Adam victim){
		victim.CheckOpponent(this);
	};
	
	public void CheckOpponent(Adam enemy){};
	
	public void CheckOpponent(Knight enemy){};
	
	public void CheckOpponent(Rogue enemy){};
	
	public void CheckOpponent(Pyromancer enemy){};
	
	public void CheckOpponent(Wizard enemy){};
	
	public void Abillity1(Adam victim){};
	
	public void Abillity2(Adam victim){};
	
	public void restoreDmg(){};
}
