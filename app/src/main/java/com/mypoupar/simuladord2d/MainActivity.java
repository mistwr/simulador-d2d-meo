package com.mypoupar.simuladord2d;

import android.app.*;import android.os.*;import android.content.*;import android.graphics.*;import android.graphics.drawable.*;import android.view.*;import android.widget.*;import java.util.*;

public class MainActivity extends Activity {
  public void onCreate(Bundle b){super.onCreate(b);getWindow().setFlags(1024,1024);setContentView(new GameView(this));}
}

class GameView extends View {
  Paint p=new Paint(3); int xp=0,trust=52,meo=0,energy=0,door=-1,step=0; RectF[] houses=new RectF[8];
  String line="Escolhe uma casa e bate à porta para começar.";
  String[][] answers={{"Compreendo. Posso perguntar quanto paga?","A MEO é melhor. Quer aderir?","Deixo o contacto e volto depois."},{"Se mantiver tudo e poupar, quer comparar?","Tem mesmo de mudar.","E na eletricidade, quanto paga?"},{"Confirmamos cobertura e proposta equivalente.","Assine já aqui.","Primeiro confirmo a fibra nesta morada."}};
  GameView(Context c){super(c);p.setTypeface(Typeface.create("sans",0));setBackgroundColor(Color.rgb(242,246,251));}
  void box(Canvas c,float l,float t,float r,float b,int color,float rad){p.setColor(color);c.drawRoundRect(l,t,r,b,rad,rad,p);}
  void text(Canvas c,String s,float x,float y,float size,int color,boolean bold){p.setColor(color);p.setTextSize(size);p.setTypeface(Typeface.create("sans",bold?1:0));c.drawText(s,x,y,p);}
  protected void onDraw(Canvas c){super.onDraw(c);float w=getWidth(),h=getHeight();int navy=Color.rgb(16,32,71),blue=Color.rgb(19,95,221);
    box(c,0,0,w,70,Color.WHITE,0);box(c,0,70,250,h-32,Color.WHITE,0);box(c,w-355,70,w,h-32,Color.WHITE,0);box(c,0,h-32,w,h,navy,0);
    box(c,18,14,60,56,blue,13);text(c,"M",31,45,25,Color.WHITE,true);text(c,"MISSÃO PORTA A PORTA",73,34,17,navy,true);text(c,"Treino VPP · MEO + MEO Energia",73,53,12,Color.GRAY,false);
    text(c,xp+" XP",w-280,42,15,blue,true);text(c,trust+"% confiança",w-195,42,15,navy,true);text(c,"Juntos Somos +",w-135,h-11,12,Color.rgb(255,211,56),true);
    text(c,"MISSÃO ATUAL",20,102,11,blue,true);text(c,"Conquista a zona",20,132,24,navy,true);text(c,"Faz perguntas, escuta e",20,158,13,Color.GRAY,false);text(c,"encontra a poupança real.",20,177,13,Color.GRAY,false);
    box(c,18,194,230,248,Color.rgb(255,248,215),14);text(c,"🏆 VPP Iniciante",30,219,16,navy,true);text(c,"Objetivo: 2 MEO + 2 Energia",30,239,11,Color.DKGRAY,false);
    box(c,18,262,116,325,Color.rgb(235,244,255),14);box(c,130,262,230,325,Color.rgb(235,244,255),14);text(c,meo+"/2",50,292,22,blue,true);text(c,"MEO",52,313,11,Color.GRAY,true);text(c,energy+"/2",158,292,22,blue,true);text(c,"ENERGIA",151,313,11,Color.GRAY,true);
    float ml=268,mr=w-373,mt=90,mb=h-50;box(c,ml,mt,mr,mb,Color.rgb(125,201,116),23);box(c,(ml+mr)/2-40,mt,(ml+mr)/2+40,mb,Color.rgb(89,101,114),0);box(c,ml,mt+(mb-mt)*.58f,mr,mt+(mb-mt)*.58f+65,Color.rgb(89,101,114),0);
    float[][] pos={{.08f,.12f},{.28f,.14f},{.65f,.12f},{.84f,.15f},{.08f,.72f},{.28f,.75f},{.65f,.72f},{.84f,.75f}};for(int i=0;i<8;i++){float x=ml+pos[i][0]*(mr-ml),y=mt+pos[i][1]*(mb-mt);houses[i]=new RectF(x,y,x+58,y+60);p.setColor(i==door?Color.LTGRAY:Color.rgb(255,202,72));c.drawRect(x+8,y+22,x+50,y+55,p);Path roof=new Path();roof.moveTo(x,y+25);roof.lineTo(x+29,y);roof.lineTo(x+58,y+25);roof.close();c.drawPath(roof,p);box(c,x+7,y+49,x+52,y+65,navy,6);text(c,i==door?"Visitada":"Porta "+(i+1),x+11,y+61,9,Color.WHITE,true);}
    float dl=w-335;text(c,"À PORTA",dl,107,11,blue,true);text(c,door<0?"Preparado para vender?":"Sr. Joaquim",dl,136,22,navy,true);text(c,door<0?"Cada cliente tem objeções diferentes.":"Cliente atual · TV + Net + Voz",dl,157,12,Color.GRAY,false);
    text(c,"Confiança do cliente",dl,190,11,Color.DKGRAY,true);box(c,dl,199,w-25,209,Color.LTGRAY,5);box(c,dl,199,dl+(w-25-dl)*trust/100f,209,Color.rgb(36,197,123),5);
    box(c,dl,228,w-22,318,Color.rgb(244,247,251),16);wrap(c,line,dl+14,255,15,navy,300);
    if(door>=0){text(c,"O QUE RESPONDES?",dl,348,11,Color.GRAY,true);for(int i=0;i<3;i++){float y=360+i*68;box(c,dl,y,w-22,y+57,Color.WHITE,12);p.setStyle(Paint.Style.STROKE);p.setStrokeWidth(2);p.setColor(Color.rgb(220,228,241));c.drawRoundRect(dl,y,w-22,y+57,12,12,p);p.setStyle(Paint.Style.FILL);box(c,dl+9,y+12,dl+39,y+43,Color.rgb(234,242,255),9);text(c,""+(char)('A'+i),dl+19,y+34,13,blue,true);wrap(c,answers[Math.min(step,2)][i],dl+49,y+22,12,navy,260);}}
  }
  void wrap(Canvas c,String s,float x,float y,float size,int color,float width){p.setTextSize(size);String[] ws=s.split(" ");String line="";for(String a:ws){if(p.measureText(line+a)>width){text(c,line,x,y,size,color,false);line="";y+=size+5;}line+=a+" ";}text(c,line,x,y,size,color,false);}
  public boolean onTouchEvent(android.view.MotionEvent e){if(e.getAction()!=1)return true;float x=e.getX(),y=e.getY();for(int i=0;i<8;i++)if(houses[i]!=null&&houses[i].contains(x,y)){door=i;step=i%3==0?0:1;line=step==0?"Estou bem como estou e não quero mudar.":"Pago quase 74 €, mas tenho isto há muitos anos.";invalidate();return true;}if(door>=0&&x>getWidth()-355&&y>360){int a=Math.min(2,(int)((y-360)/68));if(a==0){xp+=step==2?320:150;trust=Math.min(100,trust+18);if(step==2){meo++;line="Boa abordagem! Contrato MEO fechado.";}else{step++;line=step==1?"Pago quase 74 €, mas tenho isto há muitos anos.":"Quero futebol e telefone fixo.";}}else if(a==1){xp=Math.max(0,xp-30);trust=Math.max(0,trust-15);line="Foi demasiado rápido. Primeiro cria confiança.";}else{xp+=50;trust=Math.min(100,trust+5);if(step==1){energy++;line="Boa! Descobriste uma oportunidade de Energia.";}else line="O cliente aceitou o contacto para acompanhamento.";}invalidate();}return true;}
}
