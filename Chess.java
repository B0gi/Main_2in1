class Chess {
	private int priority; //�v��
	private int identify; //�s��
	private int color;
	private String name;
	private boolean isDie = true;
	private boolean isOpen = false; //�ȶ}�F��~
	
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
	
	/*�P�_�v��*/
	private int judgePriority(int identify){
		switch(identify){
		case 0: 
			this.priority = 6;
			if(color == 0){
				name = "�N";
			} else
				name = "��";
			break;
		case 1:
			this.priority = 5;
			if(color == 0){
				name = "�h";
			} else
				name = "�K";
			break;
		case 2:
			this.priority = 4;
			if(color == 0){
				name = "�H";
			} else
				name = "��";
			break;
		case 3:
			this.priority = 3;
			if(color == 0){
				name = "��";
			} else
				name = "��";
			break;
		case 4:
			this.priority = 2;
			if(color == 0){
				name = "��";
			} else
				name = "��";
			break;
		case 5:
			this.priority = 1;
			if(color == 0){
				name = "�]";
			} else
				name = "��";	
			break;
		case 6:
			this.priority = 0;
			if(color == 0){
				name = "��";
			} else
				name = "�L";
			break;
		}
		return priority;
	}
	public String toString(){
		String s;
		//s = "��: " + this.name + "\t�s���G " + identify + "\t�v���G " + priority +" \n";
		s = this.name;
		return s;
	}
}

class AllChess{
	private Chess A1 = new Chess(0, 0);//�N
	private Chess A2 = new Chess(0, 1);//�h
	private Chess A3 = new Chess(0, 1);//�h
	private Chess A4 = new Chess(0, 2);//�H
	private Chess A5 = new Chess(0, 2);//�H
	private Chess A6 = new Chess(0, 3);//��
	private Chess A7 = new Chess(0, 3);//��
	private Chess A8 = new Chess(0, 4);//��
	private Chess A9 = new Chess(0, 4);//��
	private Chess A10 = new Chess(0, 5);//�]
	private Chess A11 = new Chess(0, 5);//�]
	private Chess A12 = new Chess(0, 6);//��
	private Chess A13 = new Chess(0, 6);//��
	private Chess A14 = new Chess(0, 6);//��
	private Chess A15 = new Chess(0, 6);//��
	private Chess A16 = new Chess(0, 6);//��
	
	private Chess B1 = new Chess(1, 0);//��
	private Chess B2 = new Chess(1, 1);//�K
	private Chess B3 = new Chess(1, 1);//�K
	private Chess B4 = new Chess(1, 2);//��
	private Chess B5 = new Chess(1, 2);//��
	private Chess B6 = new Chess(1, 3);//����
	private Chess B7 = new Chess(1, 3);//����
	private Chess B8 = new Chess(1, 4);//����
	private Chess B9 = new Chess(1, 4);//����
	private Chess B10 = new Chess(1, 5);//��
	private Chess B11 = new Chess(1, 5);//��
	private Chess B12 = new Chess(1, 6);//�L
	private Chess B13 = new Chess(1, 6);//�L
	private Chess B14 = new Chess(1, 6);//�L
	private Chess B15 = new Chess(1, 6);//�L
	private Chess B16 = new Chess(1, 6);//�L
	private Chess[][] chess = 
		{{A1, A2, A3, A4, A5, A6, A7, A8, A9, A10, A11, A12, A13, A14, A15, A16},
		{B1, B2, B3, B4, B5, B6, B7, B8, B9, B10, B11, B12, B13, B14, B15, B16}};
	
	public Chess getChess(int color, int index){
		return chess[color][index];
	}
}