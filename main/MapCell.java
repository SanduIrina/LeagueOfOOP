package main;

public class MapCell {
	int x,y,free;
	char type;
	public MapCell(int x, int y, char type){
		this.x = x;
		this.y = y;
		this.type = type;
		this.free = 0;
	}
	
	public void SetFree(){
		free ++;
	}
}
