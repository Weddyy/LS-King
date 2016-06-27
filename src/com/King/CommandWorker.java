package com.King;


import com.King.module.ClientInfo;
import com.King.module.STS;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by Wed on 10.02.16.
 */
public class CommandWorker extends Thread {

    @Override
    public void run()
    {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            for (; ; ) {
                String line = in.readLine();
                if (line == null) {
                    break;
                }
                if (line.equals("info")) {
                    MainInformation.Initialize().giveAllInfo();
                }else if (line.startsWith("mon")) {
                    String[] st=line.split(" ");
                    if(st.length==2)
                    {
                        try{
                            int coutn=Integer.parseInt(st[1]);
                            ClientInfo info = MainInformation.Initialize().getMonkeyList().get(coutn).getHWInfo();
                            System.out.println("=======Info Monkey "+coutn+"========");
                            System.out.println("CPU Idle time "+String.valueOf(info.getCPU_IdleTime()));
                            System.out.println("Count users "+String.valueOf(info.getCountUsers()));
                            System.out.println("Mem free "+String.valueOf(info.getMem_free()));
                            System.out.println("Mem used "+String.valueOf(info.getMem_used()));
                            System.out.println("Swap free "+String.valueOf(info.getSwap_free()));
                            System.out.println("Swap used "+String.valueOf(info.getSwap_used()));
                            System.out.println("Time for one round "+String.valueOf(info.getTimeForOneRound()));
                            System.out.println("Time last request API "+String.valueOf(info.getTimeLastRequestonAPI()));
                            System.out.println("Time last request info "+String.valueOf(info.getTimeLastRequestonInfo()));
                            System.out.println("IP "+MainInformation.Initialize().getMonkeyList().get(coutn).getChanel().remoteAddress().toString());
                        }catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                }else if (line.startsWith("cam")) {
                    String[] st=line.split(" ");
                    if(st.length==2)
                    {
                        try{
                            int coutn=Integer.parseInt(st[1]);
                            ClientInfo info = MainInformation.Initialize().getListCamera().get(coutn).getHWInfo();
                            System.out.println("=======Info Camera "+coutn+"========");
                            System.out.println("CPU Idle time "+String.valueOf(info.getCPU_IdleTime()));
                            System.out.println("Count users "+String.valueOf(info.getCountUsers()));
                            System.out.println("Mem free "+String.valueOf(info.getMem_free()));
                            System.out.println("Mem used "+String.valueOf(info.getMem_used()));
                            System.out.println("Swap free "+String.valueOf(info.getSwap_free()));
                            System.out.println("Swap used "+String.valueOf(info.getSwap_used()));
                            System.out.println("Time for one round "+String.valueOf(info.getTimeForOneRound()));
                            System.out.println("Time last request API "+String.valueOf(info.getTimeLastRequestonAPI()));
                            System.out.println("Time last request info "+String.valueOf(info.getTimeLastRequestonInfo()));
                            System.out.println("IP "+MainInformation.Initialize().getMonkeyList().get(coutn).getChanel().remoteAddress().toString());
                        }catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                }else if (line.startsWith("sts")) {
                    if(STS.Initializ()==null) {
                        System.out.println("STS Server Disable");
                        return;
                    }
                        try{
                            ClientInfo info = STS.Initializ().getHWInfo();
                            System.out.println("=======Info STS========");
                            System.out.println("CPU Idle time"+String.valueOf(info.getCPU_IdleTime()));
                            System.out.println("Count users"+String.valueOf(info.getCountUsers()));
                            System.out.println("Mem free"+String.valueOf(info.getMem_free()));
                            System.out.println("Mem used"+String.valueOf(info.getMem_used()));
                            System.out.println("Swap free"+String.valueOf(info.getSwap_free()));
                            System.out.println("Swap used"+String.valueOf(info.getSwap_used()));
                            System.out.println("Time for one round"+String.valueOf(info.getTimeForOneRound()));
                            System.out.println("Time last request API"+String.valueOf(info.getTimeLastRequestonAPI()));
                            System.out.println("Time last request info"+String.valueOf(info.getTimeLastRequestonInfo()));
                            System.out.println("IP "+STS.Initializ().getChanel().remoteAddress().toString());
                        }catch (Exception e)
                        {
                            e.printStackTrace();
                        }

                }else if (line.startsWith("cls")) {
                    if( System.getProperty( "os.name" ).startsWith( "Window" ) )
                        Runtime.getRuntime().exec("cls");
                    else
                        Runtime.getRuntime().exec("clear");

                }
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
