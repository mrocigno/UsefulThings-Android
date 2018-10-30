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
 *                       | |    /_/  |/V\|  \_\    | |
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

package lib.rocigno.usefulthingslib.CustomViews.Views;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;


import java.util.ArrayList;
import java.util.Objects;

import lib.rocigno.usefulthingslib.CustomViews.Adapters.SectionsPagerAdapter;
import lib.rocigno.usefulthingslib.CustomViews.BottomSheets.CustomBottomSheetBehavior;
import lib.rocigno.usefulthingslib.CustomViews.Controllers.Cartoes;
import lib.rocigno.usefulthingslib.CustomViews.Models.CardsModel;
import lib.rocigno.usefulthingslib.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PagamentoFragment extends Fragment {

    View view;
    ViewPager vp_cartoes;
    SectionsPagerAdapter mSectionsPagerAdapter;
    CustomBottomSheetBehavior cbsb = new CustomBottomSheetBehavior();
    Button pagamentos_btnAdd, addCard_btnEnd;
    ImageView addCard_imgCbr;
    RadioGroup pagamentos_radioGroup;
    ArrayList<RadioButton> radios = new ArrayList<>();
    CardActions cardActions;

    NestedScrollView pagamentos_nestMain;
    LinearLayout addCard_lnlMain;
    CoordinatorLayout addCard_cooLay;

    public interface CardActions{
        void onAddCard(CardsModel model);
        void onRemoveCard();
    }

    public PagamentoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if(view == null){
            view = inflater.inflate(R.layout.fragment_pagamento, container, false);
            initVars();
            initActions();
            initBehavior();
        }

        return view;
    }
    
    private void initVars(){
        pagamentos_nestMain = view.findViewById(R.id.pagamentos_nestMain);
        vp_cartoes = view.findViewById(R.id.vp_cartoes);
        mSectionsPagerAdapter = new SectionsPagerAdapter(Objects.requireNonNull(getActivity()).getSupportFragmentManager());
        vp_cartoes.setAdapter(mSectionsPagerAdapter);
        addCard_lnlMain = view.findViewById(R.id.addCard_lnlMain);
        addCard_cooLay = view.findViewById(R.id.addCard_cooLay);
        pagamentos_btnAdd = view.findViewById(R.id.pagamentos_btnAdd);
        pagamentos_radioGroup = view.findViewById(R.id.pagamentos_radioGroup);
    }


    private void initActions(){
        vp_cartoes.setPageMargin((getResources().getDimensionPixelOffset(R.dimen.add_car_behavior) * -1));
        vp_cartoes.setOffscreenPageLimit(3);

        vp_cartoes.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {}

            @Override
            public void onPageSelected(int i) {
                RadioButton radio = radios.get(i);
                pagamentos_radioGroup.check(radio.getId());
            }

            @Override
            public void onPageScrollStateChanged(int i) {}
        });

        pagamentos_radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                for (int i = 0; i < radios.size(); i++) {
                    if(radios.get(i).getId() == checkedId){
                        vp_cartoes.setCurrentItem(i);
                        return;
                    }
                }
            }
        });

        cbsb.init(addCard_lnlMain).setHideable(true).setState(CustomBottomSheetBehavior.HIDING).setActions(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View view, int i) {}

            @Override
            public void onSlide(@NonNull View view, float v) {
                addCard_cooLay.setAlpha(v);
            }
        });

        pagamentos_btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cbsb.setState(CustomBottomSheetBehavior.SHOWING);
            }
        });
    }

    private void initCartoes(){
        pagamentos_nestMain.setVisibility(View.VISIBLE);
        cbsb.setState(CustomBottomSheetBehavior.HIDING);

        RelativeLayout.LayoutParams lp = ((RelativeLayout.LayoutParams)pagamentos_btnAdd.getLayoutParams());
        lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        pagamentos_btnAdd.setLayoutParams(lp);
    }

    private void initBehavior(){
        addCard_btnEnd = view.findViewById(R.id.addcard_btnEnd);
        addCard_imgCbr = view.findViewById(R.id.addcard_imgClose);
        addCard_btnEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CardsModel cardsModel = new CardsModel(
                        "0000000000000000",
                        "Visa",
                        "",
                        "",
                        0);
                addCartao(cardsModel);
                try{cardActions.onAddCard(cardsModel);}catch (Exception ignore){}

            }
        });
        addCard_imgCbr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cbsb.setState(CustomBottomSheetBehavior.HIDING);
            }
        });
    }

    private void addCartao(CardsModel cardsModel){
        mSectionsPagerAdapter.addFragment(new Cartoes().newInstance(cardsModel));
        RadioButton radio = new RadioButton(getContext());
        pagamentos_radioGroup.addView(radio);
        radios.add(radio);
        initCartoes();
    }

    public void setCartoes(CardsModel... cards){
        for(CardsModel card:cards){
            addCartao(card);
        }
    }

    public void setActions(CardActions cardActions){
        this.cardActions = cardActions;
    }

}
