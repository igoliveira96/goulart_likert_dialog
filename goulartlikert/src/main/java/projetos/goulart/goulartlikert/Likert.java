package projetos.goulart.goulartlikert;

import android.app.Activity;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import projetos.goulart.goulartlikert.constants.LikertEnum;
import projetos.goulart.goulartlikert.interfaces.CallbackLikert;

/**
 * Criado por Igor Goulart de Oliveira em 08/06/2018.
 */
public class Likert {

    private final Activity activity;
    private final CallbackLikert callbackLikert;
    private final String title;
    private final int textColor;
    private final int backgroundColor;
    private final int itemColor;
    private AlertDialog alerta;

    private Likert(Builder build) {
        this.activity = build.activity;
        this.callbackLikert = build.callbackLikert;
        this.title = build.title;
        this.textColor = build.textColor;
        this.backgroundColor = build.backgroundColor;
        this.itemColor = build.itemColor;
    }

    public void show(){
        exibirAlerta();
    }

    private void exibirAlerta(){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity, R.style.Theme_AppCompat_Light_Dialog_Alert);
        alerta = builder.create();
        LayoutInflater inflater = activity.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.goulart_likert_layout, null);

        final CardView cardView = dialogView.findViewById(R.id.cardViewLikert);
        final TextView titleTV = dialogView.findViewById(R.id.tituloLikert);

        if(backgroundColor != -1){
            cardView.setCardBackgroundColor(activity.getResources().getColor(backgroundColor));
        }

        if(textColor != -1){
            titleTV.setTextColor(activity.getResources().getColor(textColor));
        }

        if(title != null && !title.trim().isEmpty()){
            titleTV.setText(title);
        }

        final ImageView otimo = dialogView.findViewById(R.id.otimoLikert);
        setColorItem(otimo);
        setEventClink(otimo, LikertEnum.OTIMO);

        final ImageView bom = dialogView.findViewById(R.id.bomLikert);
        setColorItem(bom);
        setEventClink(bom, LikertEnum.BOM);

        final ImageView regular = dialogView.findViewById(R.id.regularLikert);
        setColorItem(regular);
        setEventClink(regular, LikertEnum.REGULAR);

        final ImageView fraco = dialogView.findViewById(R.id.fracoLikert);
        setColorItem(fraco);
        setEventClink(fraco, LikertEnum.FRACO);

        final ImageView insatisfeito = dialogView.findViewById(R.id.insatisfeitoLikert);
        setColorItem(insatisfeito);
        setEventClink(insatisfeito, LikertEnum.INSATISFEITO);

        alerta.setView(dialogView);
        alerta.setCancelable(false);
        alerta.show();
    }

    private void setEventClink(View view, final LikertEnum likertEnum){
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnCodeLiket(likertEnum);
            }
        });
    }

    private void setColorItem(ImageView imageView){
        if(itemColor != -1){
            imageView.setColorFilter(itemColor);
        }
    }

    private void returnCodeLiket(LikertEnum likertEnum){
        alerta.dismiss();
        callbackLikert.callbackLikert(likertEnum);
    }

    public static class Builder {

        private final Activity activity;
        private final CallbackLikert callbackLikert;
        private String title;
        private int textColor;
        private int backgroundColor;
        private int itemColor;

        public Builder(Activity activity, CallbackLikert callbackLikert){
            this.activity = activity;
            this.callbackLikert = callbackLikert;
            this.backgroundColor = -1;
            this.textColor = -1;
            this.itemColor = -1;
        }

        public Builder backgroundColor(int color){
            backgroundColor = color;
            return this;
        }

        public Builder textColor(int color){
            textColor = color;
            return this;
        }

        public Builder itemColor(int color){
            itemColor = color;
            return this;
        }

        public Builder title(String title){
            this.title = title;
            return this;
        }

        public Likert build() {
            return new Likert(this);
        }

    }

}
