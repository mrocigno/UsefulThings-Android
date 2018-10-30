/*
 * Copyright (c) 2018. Created by mrocigno
 *                                     _
 *                                    /_\
 *                       _            )_(            _
 *                       |`-.___,.-~'`|=|`'~-.,___,-'|
 *                       |  __________|=|__________  |
 *                       | |          |=|          | |
 *                       | |          |=|          | |
 *                       | |          |=|          | |
 *                       | |        ,-|_|-.        | |
 *                       | |      ,' _____ ',      | |
 *                       | |     / ,'| A |'. \     | |
 *                       | |    /_// |/V\| \\_\    | |
 *            ___________|_|_________|___|_________|_|__________
 *           |             ____            _                    |
 *           |   _ __ ___ |  _ \ ___   ___(_) __ _ _ __   ___   |
 *           |  | '_ ` _ \| |_) / _ \ / __| |/ _` | '_ \ / _ \  |
 *           |  | | | | | |  _ < (_) | (__| | (_| | | | | (_) | |
 *           |  |_| |_| |_|_| \_\___/ \___|_|\__, |_| |_|\___/  |
 *           |                                |___/             |
 *           '--------------------------------------------------'
 *                       \ \         | | |         / /
 *                        \ \        | | |        / /
 *                         \ \       | | |       / /
 *                          `.`.     | | |     ,','
 *                            `.`.   | | |   ,','
 *                              `.`-.| | |,-','
 *                                `-.| | |,-'
 *                                   | | |
 *                                   | | |
 *                                   | | |
 *                                   | | |
 *                                   | | |
 *                                    \|/
 *                                     V
 */

package lib.rocigno.usefulthingslib.CustomViews.Controllers;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import lib.rocigno.usefulthingslib.CustomViews.CustomAlerts.AlertTop;
import lib.rocigno.usefulthingslib.CustomViews.Models.CardsModel;
import lib.rocigno.usefulthingslib.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Cartoes extends Fragment {

    CardsModel cartao;
    TextView cartoes_txtFinal, cartoes_txtVencimento, cartoes_txtRemovido;
    LinearLayout cartoes_lnlRemoveCard, cartoes_lnlData, cartoes_lnlMain;

    public Cartoes() {
        // Required empty public constructor
    }

    View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (v == null) {
            v = inflater.inflate(R.layout.fragment_cartoes, container, false);
            initVars();
            initActions();
        }

        return v;
    }

    private void initVars() {
        cartoes_txtFinal = v.findViewById(R.id.cartoes_txtEnd);
        cartoes_txtVencimento = v.findViewById(R.id.cartoes_txtMaturity);
        cartoes_lnlRemoveCard = v.findViewById(R.id.cartoes_lnlRemoveCard);
        cartoes_lnlData = v.findViewById(R.id.cartoes_lnlData);
        cartoes_lnlMain = v.findViewById(R.id.cartoes_lnlMain);
        cartoes_txtRemovido= v.findViewById(R.id.cartoes_txtRemovido);
    }

    private void initActions() {
        cartoes_txtFinal.setText(cartao.getNumero().substring(12, 16));
        cartoes_txtVencimento.setText(cartao.getVencimento());
        cartoes_lnlRemoveCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertTop.CustomYesNoTopAlert(getActivity(), "Você tem certeza?", "Depois de apagado os dados serão irrecuperaveis", R.drawable.ic_warning, new AlertTop.YesNoCallBack() {
                    @Override
                    public void onClickYes() {
                        cartoes_lnlMain.setBackground(getActivity().getDrawable(R.drawable.background_cartoes_x));
                        cartoes_lnlData.setVisibility(View.GONE);
                        cartoes_txtRemovido.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onClickNo() {

                    }
                });
            }
        });
    }

    public Fragment newInstance(CardsModel cartao){
        //Como o fragment precisa de um construtor vazio, para passar informações no contrutor é preciso
        //deste metodo "newInstace" que retorna o fragment. É como se fosse um construtor, só que com mais palavras.
        //Neste caso eu uso para passar os dados cartão.
        this.cartao = cartao;
        return this;
    }

}
