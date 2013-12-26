class Chess {
	private int priority; //權重
	private int identify; //編號
	private int color;
	private String name;
	private boolean isDie = true;
	private boolean isOpen = false; //掀開了嗎~
	
	public Chess(int color, int identify){
		setColor(color);
		setIdentify(identify);
		setPriority();
	}
	
	/* Set Methods */
	public void setColor(int color){
		this.color = color;
	}
	public void setIdentify(int identify){
		this.identify = identify;
	}
	public void setPriority(){
		this.priority = judgePriority(identify);
	}
	public void setDie(boolean change){
		this.isDie = change;
	}
	public void setState(boolean change){
		isOpen = change;
	}
	
	/* Get Methods */
	public int getColor(){
		return color;
	}
	public int getPriority(){
		return priority;
	}
	public int getIdentify(){
		return identify;
	}
	public boolean isDie(){
		return isDie;
	}
	public boolean getState(){
		return isOpen;
	}
	
	/*判斷權重*/
	private int judgePriority(int identify){
		switch(identify){
		case 0: 
			this.priority = 6;
			if(color == 0){
				name = "將";
			} else
				name = "帥";
			break;
		case 1:
			this.priority = 5;
			if(color == 0){
				name = "士";
			} else
				name = "仕";
			break;
		case 2:
			this.priority = 4;
			if(color == 0){
				name = "象";
			} else
				name = "相";
			break;
		case 3:
			this.priority = 3;
			if(color == 0){
				name = "車";
			} else
				name = "硨";
			break;
		case 4:
			this.priority = 2;
			if(color == 0){
				name = "馬";
			} else
				name = "瑪";
			break;
		case 5:
			this.priority = 1;
			if(color == 0){
				name = "包";
			} else
				name = "炮";	
			break;
		case 6:
			this.priority = 0;
			if(color == 0){
				name = "卒";
			} else
				name = "兵";
			break;
		}
		return priority;
	}
	public String toString(){
		String s;
		//s = "棋: " + this.name + "\t編號： " + identify + "\t權重： " + priority +" \n";
		s = this.name;
		return s;
	}
}

class AllChess{
	private Chess A1 = new Chess(0, 0);//將
	private Chess A2 = new Chess(0, 1);//士
	private Chess A3 = new Chess(0, 1);//士
	private Chess A4 = new Chess(0, 2);//象
	private Chess A5 = new Chess(0, 2);//象
	private Chess A6 = new Chess(0, 3);//車
	private Chess A7 = new Chess(0, 3);//車
	private Chess A8 = new Chess(0, 4);//馬
	private Chess A9 = new Chess(0, 4);//馬
	private Chess A10 = new Chess(0, 5);//包
	private Chess A11 = new Chess(0, 5);//包
	private Chess A12 = new Chess(0, 6);//卒
	private Chess A13 = new Chess(0, 6);//卒
	private Chess A14 = new Chess(0, 6);//卒
	private Chess A15 = new Chess(0, 6);//卒
	private Chess A16 = new Chess(0, 6);//卒
	
	private Chess B1 = new Chess(1, 0);//帥
	private Chess B2 = new Chess(1, 1);//仕
	private Chess B3 = new Chess(1, 1);//仕
	private Chess B4 = new Chess(1, 2);//相
	private Chess B5 = new Chess(1, 2);//相
	private Chess B6 = new Chess(1, 3);//紅車
	private Chess B7 = new Chess(1, 3);//紅車
	private Chess B8 = new Chess(1, 4);//紅馬
	private Chess B9 = new Chess(1, 4);//紅馬
	private Chess B10 = new Chess(1, 5);//炮
	private Chess B11 = new Chess(1, 5);//炮
	private Chess B12 = new Chess(1, 6);//兵
	private Chess B13 = new Chess(1, 6);//兵
	private Chess B14 = new Chess(1, 6);//兵
	private Chess B15 = new Chess(1, 6);//兵
	private Chess B16 = new Chess(1, 6);//兵
	private Chess[][] chess = 
		{{A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16},
		{B1, B2, B3, B4, B5, B6, B7, B8, B9, B10, B11, B12, B13, B14, B15, B16}};
	
	public Chess getChess(int color, int index){
		return chess[color][index];
	}
}