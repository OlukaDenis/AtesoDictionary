package com.mcdenny.atesodictionary;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

//This is a custom ArrayAdapter that can provide layout of the list view based on the data source
public class WordAdapter extends BaseAdapter implements Filterable {
    public Context context;
    private ArrayList<Word> originalListWords;
    private ArrayList<Word> displayListWords;
    LayoutInflater inflater;


    public WordAdapter(Activity context, ArrayList<Word> listWords){
        this.context = context;
        this.originalListWords = listWords;
        this.displayListWords = listWords;
        inflater = LayoutInflater.from(context);
    }

    private static class WordHolder {
        TextView atesotxt, englishtxt, atesoStnc, englishStnc;
        LinearLayout LvContainer;
    }

    @Override
    public int getCount() {
        return displayListWords.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, final View counterView, ViewGroup parent){

        WordHolder holder = null;
        View listItemView = counterView;

        if (listItemView == null) {
            listItemView = inflater.inflate(R.layout.word_list, parent, false);
            holder = new WordHolder();
            holder.LvContainer = (LinearLayout) listItemView.findViewById(R.id.listContainer);
            holder.atesotxt = (TextView) listItemView.findViewById(R.id.teso_word);
            holder.englishtxt = (TextView) listItemView.findViewById(R.id.eng_word);
            holder.atesoStnc = (TextView) listItemView.findViewById(R.id.aSentence);
            holder.englishStnc = (TextView) listItemView.findViewById(R.id.eSentence);

            listItemView.setTag(holder);

        } else {
            holder = (WordHolder) listItemView.getTag();
        }

        holder.atesotxt.setText(displayListWords.get(position).getAtesoTranslation());
        holder.englishtxt.setText(displayListWords.get(position).getEnglishTranslation());
        holder.atesoStnc.setText(displayListWords.get(position).getAtesoSentence());
        holder.englishStnc.setText(displayListWords.get(position).getEnglishSentence());

        listItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent intent = new Intent(context, DetailsActivity.class);


            Bundle bundle = new Bundle();
            bundle.putString("ateso_key",displayListWords.get(position).getAtesoTranslation());
            bundle.putString("english_key", displayListWords.get(position).getEnglishTranslation());
            bundle.putString("ateso_sentence_key", displayListWords.get(position).getAtesoSentence());
            bundle.putString("english_sentence_key", displayListWords.get(position).getEnglishSentence());
            intent.putExtras(bundle);
            context.startActivity(intent);
            }

        });

        //return the whole list layout (containing two layouts)
        return listItemView;
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                displayListWords = (ArrayList<Word>) filterResults.values; //has the filtered values
                notifyDataSetChanged(); //notifies the data with the filtered values
            }

            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                FilterResults filterResults = new FilterResults(); //this holds the result of the filtered words
                ArrayList<Word> arrayFilteredWords = new ArrayList<Word>();

                if(originalListWords == null){
                    originalListWords = new ArrayList<Word>(displayListWords); //the original values are saved in originalListwords
                }
                //set the original list of words to be returned
                if(charSequence == null || charSequence.length() == 0){
                    filterResults.count = originalListWords.size();
                    filterResults.values = originalListWords;
                }
                else {
                    charSequence = charSequence.toString().toLowerCase();
                    for(int i = 0; i<originalListWords.size(); i++){
                        String data = originalListWords.get(i).getEnglishTranslation();
                        String data1 = originalListWords.get(i).getAtesoTranslation();
                        if (data.toLowerCase().startsWith(charSequence.toString())){
                            arrayFilteredWords.add(new Word(
                                    originalListWords.get(i).getEnglishTranslation(),
                                    originalListWords.get(i).getAtesoTranslation(),
                                    originalListWords.get(i).getEnglishSentence(),
                                    originalListWords.get(i).getAtesoSentence(),
                                    originalListWords.get(i).getResourceAudio()));
                        }
                        else if (data1.toLowerCase().startsWith(charSequence.toString())){
                            arrayFilteredWords.add(new Word(
                                    originalListWords.get(i).getEnglishTranslation(),
                                    originalListWords.get(i).getAtesoTranslation(),
                                    originalListWords.get(i).getEnglishSentence(),
                                    originalListWords.get(i).getAtesoSentence(),
                                    originalListWords.get(i).getResourceAudio()));
                        }
                    }
                    //set the filtered list of words to be returned
                    filterResults.count = arrayFilteredWords.size();
                    filterResults.values = arrayFilteredWords;
                }
                return filterResults;
            }
        };

        return filter;
    }
}
