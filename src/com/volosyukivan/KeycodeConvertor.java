/**
 * WiFi Keyboard - Remote Keyboard for Android.
 * Copyright (C) 2011 Ivan Volosyuk
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.

 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package com.volosyukivan;

import android.view.KeyEvent;

public final class KeycodeConvertor {
  public static int convertKey(int code) {
    // public static final int KEYCODE_A = 29;
    // ...
    // public static final int KEYCODE_Z = 54;
    if (code >= 65 && code <= 90) {
      return code - 65 + KeyEvent.KEYCODE_A;
    }

    // public static final int KEYCODE_0 = 7;
    // ...
    // public static final int KEYCODE_9 = 16;
    if (code >= 48 && code <= 57) {
      return code - 48 + KeyEvent.KEYCODE_0;
    }

    switch (code) {
      case 9: return KeyEvent.KEYCODE_TAB;
      case 32: return KeyEvent.KEYCODE_SPACE;
      case 188: return KeyEvent.KEYCODE_COMMA;
      case 190: return KeyEvent.KEYCODE_PERIOD;
      case 13: return KeyEvent.KEYCODE_ENTER;
      case 219: return KeyEvent.KEYCODE_LEFT_BRACKET;
      case 221: return KeyEvent.KEYCODE_RIGHT_BRACKET;
      case 220: return KeyEvent.KEYCODE_BACKSLASH;
      // fix SEMICOLON key: prev 186
      case 59: return KeyEvent.KEYCODE_SEMICOLON;
      // add GRAVE key: code 192
      case 192: return KeyEvent.KEYCODE_GRAVE;
      case 222: return KeyEvent.KEYCODE_APOSTROPHE;
      case 8: return KeyEvent.KEYCODE_DEL;
      // fix MINUS key: prev 189
      case 173: return KeyEvent.KEYCODE_MINUS;
      // fix EQUAL key: prev 187
      case 61: return KeyEvent.KEYCODE_EQUALS;
      case 191: return KeyEvent.KEYCODE_SLASH;
      case 18: return KeyEvent.KEYCODE_ALT_LEFT;
      case 16: return KeyEvent.KEYCODE_SHIFT_LEFT;

      // arrow keys
      case 38: return KeyEvent.KEYCODE_DPAD_UP;
      case 40: return KeyEvent.KEYCODE_DPAD_DOWN;
      case 37: return KeyEvent.KEYCODE_DPAD_LEFT;
      case 39: return KeyEvent.KEYCODE_DPAD_RIGHT;

      // ESC
      case 27: return KeyEvent.KEYCODE_ESCAPE;
      // Back
      case 112: return KeyEvent.KEYCODE_BACK;
      // Home
      case 113: return KeyEvent.KEYCODE_HOME;
      // Menu
      case 114: return KeyEvent.KEYCODE_MENU;
      // Play, Previous, Next
      case 117: return KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE;
      case 118: return KeyEvent.KEYCODE_MEDIA_PREVIOUS;
      case 119: return KeyEvent.KEYCODE_MEDIA_NEXT;
      // Volume Up / Down
      case 121: return KeyEvent.KEYCODE_VOLUME_UP;
      case 120: return KeyEvent.KEYCODE_VOLUME_DOWN;
      // Search
      case 122: return KeyEvent.KEYCODE_SEARCH;

      // PgUp, PgDown
      case 33: return KeyEvent.KEYCODE_PAGE_UP;
      case 34: return KeyEvent.KEYCODE_PAGE_DOWN;

      // Insert, delete
      case 45: return KeyEvent.KEYCODE_INSERT;
      case 46: return WiFiInputMethod.KEY_DEL;

      case KeyboardHttpServer.FOCUS: return KeyboardHttpServer.FOCUS;
      case 17: return WiFiInputMethod.KEY_CONTROL;

      default: return -1;
    }
  }
}
