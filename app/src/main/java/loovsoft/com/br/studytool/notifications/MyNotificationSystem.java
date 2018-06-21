package loovsoft.com.br.studytool.notifications;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import loovsoft.com.br.studytool.R;
import loovsoft.com.br.studytool.fragments.HorarioFragment;

public class MyNotificationSystem extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        //Cria a notificação
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(context.getString(R.string.notification_title))
                .setContentText(context.getString(R.string.notification_text))
                .setAutoCancel(true);

        //Checa a versão e define se deve ser colocado um ícone grande na notificação
        //(Isso pode ser feito para qualquer versão, mas eu acho que não fica muito bom em versões
        //mais antigas do Android).
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Bitmap bm = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher);
            mBuilder.setLargeIcon(bm);
        }

        //Cria uma intent indicanado que activity será chamada quando a notificação for clicada
        Intent resultIntent = new Intent(context, HorarioFragment.class);

        //Se a activity aberta pela notificação não for exclusiva da notificação é necessário criar
        //definir uma pilha
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(HorarioFragment.class);
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(resultPendingIntent);

        //Envia a noitificação para o usuário
        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(5, mBuilder.build());
    }
}