package com.ross.glasseyes;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.KeyEvent;
import android.view.View;
//import android.widget.TextView;

import com.google.android.glass.app.Card;
import com.google.android.glass.media.Sounds;

public class EyesActivity extends Activity implements TextToSpeech.OnInitListener {

	  private Card _card;
	  private View _cardView;
	  //private TextView _statusTextView;
	  //private String chase = "Chase sux!!";
	  private String chase_sux[] = { "chase blowz", "chase is a tool", "chase is lame"};
	  private int i = 0;
	  

	  private TextToSpeech _speech;
	  private Context _context = this;

	  
	  private void displayChaseView() {
		    _card.setText(chase_sux[i]);
		    //_card.setImageLayout(Card.ImageLayout.LEFT);
		    //_card.clearImages();
		    //_card.addImage(R.drawable.chase);
		    _cardView = _card.getView();
		    setContentView(_cardView);
	  }
	  
	  @Override
	  public void onInit(int status) {
		  _speech.speak(chase_sux[i], TextToSpeech.QUEUE_FLUSH, null);
	  }
	  
	  @Override
	  protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);

	    // Even though the text-to-speech engine is only used in response to a menu action, we
	    // initialize it when the application starts so that we avoid delays that could occur
	    // if we waited until it was needed to start it up.
	    /*_speech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
	      @Override
	      public void onInit(int status) {
	        _speech.speak(chase, TextToSpeech.QUEUE_FLUSH, null);
	      }
	    });*/
	    
	    _speech = new TextToSpeech(this, this);
	    
	    
	    _card = new Card(_context);
	    displayChaseView();
	    
	    /*_card.setText(chase_sux[i]);
	    _card.setImageLayout(Card.ImageLayout.LEFT);
	    _card.addImage(R.drawable.chase);
	    _cardView = _card.getView();
	    setContentView(_cardView);*/

	    // An alternative way to layout the UX
	    //setContentView(R.layout.layout_helloworld);
	    //_statusTextView = (TextView)findViewById(R.id.status);
	  }

	  /**
	   * Handle the tap event from the touchpad.
	   */
	  @Override
	  public boolean onKeyDown(int keyCode, KeyEvent event) {
	    switch (keyCode) {
	    // Handle tap events.
	    case KeyEvent.KEYCODE_DPAD_CENTER:
	    case KeyEvent.KEYCODE_ENTER:

	      // Change the text of the card when the touchpad is touched
	      //_card.setText(R.string.touchpad_touched);
	      //_cardView = _card.toView();
	      //setContentView(_cardView);

	      // Status message below the main text in the alternative UX layout
	      AudioManager audio = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
	      audio.playSoundEffect(Sounds.TAP);
	      
	      
	      //_card.setFootnote(R.string.touchpad_touched);
	      //setContentView(_card.getView());
	      //_statusTextView.setText(R.string.touchpad_touched);

	      //_speech.speak("Touchpad touched", TextToSpeech.QUEUE_FLUSH, null);
	      
	      _speech.speak(chase_sux[++i], TextToSpeech.QUEUE_FLUSH, null);
	      displayChaseView();
	      
	      return true;
	    default:
	      return super.onKeyDown(keyCode, event);
	    }
	  }

	  @Override
	  public void onResume() {
	    super.onResume();
	  }

	  @Override
	  public void onPause() {
	    super.onPause();
	  }

	  @Override
	  public void onDestroy() {
	    super.onDestroy();
	  }
	}
