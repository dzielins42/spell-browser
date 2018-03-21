package pl.dzielins42.spells.browser.view.spell.list;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hannesdorfmann.mosby3.mvi.MviFragment;

import dagger.android.support.AndroidSupportInjection;
import io.reactivex.Observable;
import pl.dzielins42.spellcontentprovider.characterclass.CharacterClassDao;
import pl.dzielins42.spellcontentprovider.component.ComponentDao;
import pl.dzielins42.spells.browser.R;
import pl.dzielins42.spells.browser.data.local.FragmentHelloService;
import pl.dzielins42.spells.browser.view.spell.list.dummy.DummyContent;
import pl.dzielins42.spells.browser.view.spell.list.dummy.DummyContent.DummyItem;
import timber.log.Timber;

import java.util.List;

import javax.inject.Inject;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class SpellListFragment
        extends MviFragment<SpellListView, SpellListPresenter>
        implements SpellListView {

    @Inject
    FragmentHelloService mFragmentHelloService;
    @Inject
    SpellListPresenter mPresenter;

    private OnListFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public SpellListFragment() {
    }

    @SuppressWarnings("unused")
    public static SpellListFragment newInstance(int columnCount) {
        SpellListFragment fragment = new SpellListFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.fragment_spell_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(new MySpellRecyclerViewAdapter(DummyContent.ITEMS, mListener));
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        Log.d(
                "SpellListFragment",
                "onResume: " + (mFragmentHelloService != null ? mFragmentHelloService.hello() : "FragmentHelloService is null")
        );
        Log.d(
                "SpellListFragment",
                "onResume: mPresenter=" + String.valueOf(mPresenter)
        );
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @NonNull
    @Override
    public SpellListPresenter createPresenter() {
        return mPresenter;
    }

    @Override
    public void render(SpellListState state) {
        Timber.d("render: "+String.valueOf(state));
    }

    @Override
    public Observable<Boolean> loadIntent() {
        return Observable.just(true);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(DummyItem item);
    }
}
