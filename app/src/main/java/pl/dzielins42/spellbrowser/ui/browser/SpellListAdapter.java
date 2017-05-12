package pl.dzielins42.spellbrowser.ui.browser;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.dzielins42.spellbrowser.R;
import pl.dzielins42.spellbrowser.data.model.Spell;
import pl.dzielins42.toolkit.android.support.v7.widget.ArrayAdapter;

public class SpellListAdapter extends
        ArrayAdapter<Spell, SpellListAdapter.ViewHolder> {

    private final SpellListAdapterListener mListener;

    public SpellListAdapter(SpellListAdapterListener listener) {
        mListener = listener;
    }

    @Override
    protected void onBindViewHolder(ViewHolder viewHolder, Spell spell) {
        viewHolder.bindData(spell);
    }

    @Override
    public SpellListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.spell_list_content, parent, false);
        return new ViewHolder(view);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final View mView;
        @BindView(R.id.id)
        TextView mIdView;
        @BindView(R.id.content)
        TextView mContentView;
        public Spell mItem;

        public ViewHolder(View itemView) {
            super(itemView);

            mView = itemView;
            itemView.setOnClickListener(this);

            ButterKnife.bind(this, itemView);
        }

        public void bindData(Spell item) {
            mItem = item;

            mIdView.setText(item.getId().toString());
            mContentView.setText(item.getName());
        }

        @Override
        public void onClick(View view) {
            mListener.onItemClicked(mItem);
        }
    }

    interface SpellListAdapterListener {
        void onItemClicked(Spell item);
    }

}
