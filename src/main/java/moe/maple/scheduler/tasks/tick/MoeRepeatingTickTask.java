/*
 * Copyright (C) 2019, y785, http://github.com/y785
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package moe.maple.scheduler.tasks.tick;

import moe.maple.scheduler.tasks.MoeTask;

public class MoeRepeatingTickTask implements MoeTask {

    private final MoeTask actual;
    private final long tickCount;
    private long iteration;

    public MoeRepeatingTickTask(MoeTask actual, long tickCount) {
        if (tickCount == 0)
            throw new IllegalArgumentException("Tick Count is 0, please.");
        this.actual = actual;
        this.tickCount = tickCount;
    }

    @Override
    public boolean isEventAsync() {
        return actual.isEventAsync();
    }

    @Override
    public boolean isEventDone() {
        return actual.isEventDone();
    }

    @Override
    public void update(long delta) {
        iteration++;

        if (iteration >= tickCount) {
            actual.update(delta);
            iteration = 0;
        }
    }
}
